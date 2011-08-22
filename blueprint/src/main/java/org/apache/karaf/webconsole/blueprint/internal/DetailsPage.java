package org.apache.karaf.webconsole.blueprint.internal;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
        final Bundle bundle = context.getBundle(params.getLong("bundleId"));

        ServiceReference[] references = bundle.getRegisteredServices();
        ServiceReference reference = null;

        for (ServiceReference ref : references) {
            if ("org.osgi.service.blueprint.container.BlueprintContainer".equals(ref.getProperty("objectClass"))) {
                reference = ref;
                break;
            }
        }

        if (reference == null) {
            return;
        }

        final BlueprintContainer container = (BlueprintContainer) context.getService(reference);

        LoadableDetachableModel<List<SerializableComponentMetadata>> model = new LoadableDetachableModel<List<SerializableComponentMetadata>>() {
            @Override
            protected List<SerializableComponentMetadata> load() {
                @SuppressWarnings("unchecked")
                Collection<ComponentMetadata> metadata = container.getMetadata(ComponentMetadata.class);

                List<SerializableComponentMetadata> serializable = new LinkedList<SerializableComponentMetadata>();
                for (ComponentMetadata componentMetadata : metadata) {
                    serializable.add(new SerializableComponentMetadata(componentMetadata));
                }

                return serializable;
            }
        };

        add(new ListView<SerializableComponentMetadata>("components", model) {
            @Override
            protected void populateItem(ListItem<SerializableComponentMetadata> item) {
                SerializableComponentMetadata metadata = item.getModelObject();

                item.add(new Label("componentId", metadata.getId()));
                item.add(new Label("type", metadata.getTypeName()));
            }
        });

        context.ungetService(reference);
    }

}
