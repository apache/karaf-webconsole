package org.apache.karaf.webconsole.osgi.blueprint.details;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

class MetadataModel extends LoadableDetachableModel<List<ComponentMetadata>> {

    private int bundleId;
    private BundleContext context;
    private ServiceReference reference;

    public MetadataModel(BundleContext context, int bundleId) {
        this.context = context;
        this.bundleId = bundleId;
    }

    @Override
    protected List<ComponentMetadata> load() {
        Bundle bundle = context.getBundle(bundleId);

        ServiceReference[] references = bundle.getRegisteredServices();
        for (ServiceReference ref : references) {
            String[] classes = (String[]) ref.getProperty("objectClass");
            for (String clazz : classes) {
                if ("org.osgi.service.blueprint.container.BlueprintContainer".equals(clazz)) {
                    reference = ref;
                    List<ComponentMetadata> metas = new ArrayList<ComponentMetadata>();
                    metas.addAll(((BlueprintContainer) context.getService(ref)).getMetadata(ComponentMetadata.class));
                    return metas;
                }
            }
        }
        throw new IllegalArgumentException("Cannot find blueprint container for bundle " + bundleId);
    }

    @Override
    public void detach() {
        if (reference != null) {
            context.ungetService(reference);
        }
    }
}