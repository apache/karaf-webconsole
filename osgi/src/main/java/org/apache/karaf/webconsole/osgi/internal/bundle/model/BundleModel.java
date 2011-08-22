package org.apache.karaf.webconsole.osgi.internal.bundle.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class BundleModel extends LoadableDetachableModel<Bundle> {

    private long bundleId;
    private BundleContext context;

    public BundleModel(long bundleId, BundleContext context) {
        this.bundleId = bundleId;
        this.context = context;
    }

    @Override
    protected Bundle load() {
        return context.getBundle(bundleId);
    }

}
