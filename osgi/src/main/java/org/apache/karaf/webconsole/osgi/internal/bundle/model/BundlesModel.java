package org.apache.karaf.webconsole.osgi.internal.bundle.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class BundlesModel extends LoadableDetachableModel<Bundle[]> {

    private final BundleContext context;

    public BundlesModel(BundleContext context) {
        this.context = context;
    }

    @Override
    protected Bundle[] load() {
        return context.getBundles();
    }

}
