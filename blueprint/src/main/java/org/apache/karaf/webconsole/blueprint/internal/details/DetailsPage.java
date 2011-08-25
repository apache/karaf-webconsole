package org.apache.karaf.webconsole.blueprint.internal.details;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

@PaxWicketMountPoint(mountPoint = "/osgi/blueprint/details")
public class DetailsPage extends BasePage {

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;

    public DetailsPage(PageParameters params) {
        IModel<List<MetadataWrappper>> model = new MetadataModel(params);
        if (model.getObject() == null) {
            fillEmptyListView();
            return;
        }
        fillListViewWithMetaModel(model);
    }

    private void fillEmptyListView() {
        add(new ListView("components") {
            @Override
            protected void populateItem(ListItem item) {
                // do nothing :)
            }
        });
    }

    private void fillListViewWithMetaModel(IModel<List<MetadataWrappper>> model) {
        add(new ListView<MetadataWrappper>("components", model) {
            @Override
            protected void populateItem(ListItem<MetadataWrappper> item) {
                MetadataWrappper metadata = item.getModelObject();
                item.add(new Label("componentId", metadata.id));
                item.add(new Label("type", metadata.interfaces));
            }
        });
    }

    private class MetadataModel extends LoadableDetachableModel<List<MetadataWrappper>> {

        private PageParameters pageParams;

        public MetadataModel(PageParameters pageParams) {
            this.pageParams = pageParams;
        }

        @Override
        protected List<MetadataWrappper> load() {
            ServiceReference reference = null;
            try {
                reference = retrieveReference();
                if (reference == null) {
                    return null;
                }
                return extractMetadata(reference);
            } finally {
                if (reference != null) {
                    context.ungetService(reference);
                }
            }
        }

        private List<MetadataWrappper> extractMetadata(ServiceReference reference) {
            BlueprintContainer container = (BlueprintContainer) context.getService(reference);
            List<MetadataWrappper> extractedMetadata = new ArrayList<DetailsPage.MetadataWrappper>();
            Collection<ComponentMetadata> metadata = container.getMetadata(ComponentMetadata.class);
            for (ComponentMetadata originalMetadata : metadata) {
                extractedMetadata.add(new MetadataWrappper(originalMetadata));
            }
            return extractedMetadata;
        }

        private ServiceReference retrieveReference() {
            ServiceReference reference = null;
            Bundle bundle = context.getBundle(pageParams.getLong("bundleId"));
            ServiceReference[] references = bundle.getRegisteredServices();
            for (ServiceReference ref : references) {
                String[] classes = (String[]) ref.getProperty("objectClass");
                for (String clazz : classes) {
                    if ("org.osgi.service.blueprint.container.BlueprintContainer".equals(clazz)) {
                        reference = ref;
                        break;
                    }
                }
            }
            return reference;
        }
    }

    private static class MetadataWrappper implements Serializable {
        private String id;
        private String interfaces;

        public MetadataWrappper(ComponentMetadata metadata) {
            id = metadata.getId();
            interfaces = Arrays.toString(metadata.getClass().getInterfaces());
        }

    }

}
