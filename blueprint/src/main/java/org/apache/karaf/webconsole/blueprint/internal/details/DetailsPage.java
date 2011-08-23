package org.apache.karaf.webconsole.blueprint.internal.details;

import java.util.ArrayList;
import java.util.Arrays;
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
            String[] classes = (String[]) ref.getProperty("objectClass");
            for (String clazz : classes) {
                if ("org.osgi.service.blueprint.container.BlueprintContainer".equals(clazz)) {
                    reference = ref;
                    break;
                }
            }
        }

        if (reference == null) {
            add(new ListView("components") {
                @Override
                protected void populateItem(ListItem item) {
                    // do nothing :)
                }
            });
            return;
        }

        final BlueprintContainer container = (BlueprintContainer) context.getService(reference);

        LoadableDetachableModel<List<ComponentMetadata>> model = new LoadableDetachableModel<List<ComponentMetadata>>() {
            @Override
            protected List<ComponentMetadata> load() {
                return new ArrayList<ComponentMetadata>(container.getMetadata(ComponentMetadata.class));
            }
        };

        add(new ListView<ComponentMetadata>("components", model) {
            @Override
            protected void populateItem(ListItem<ComponentMetadata> item) {
                ComponentMetadata metadata = item.getModelObject();

                Class<?>[] interfaces = metadata.getClass().getInterfaces();

                item.add(new Label("componentId", metadata.getId()));
                item.add(new Label("type", Arrays.toString(interfaces)));
            }
        });

        context.ungetService(reference);
    }

}
