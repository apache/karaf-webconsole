package org.apache.karaf.webconsole.osgi.internal.bundle;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.bundle.model.BundleModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class BundlesDataProvider extends SortableDataProvider<Bundle> {

    private BundleContext context;

    public BundlesDataProvider(BundleContext context) {
        this.context = context;
    }

    public Iterator<? extends Bundle> iterator(int first, int count) {
        List<Bundle> bundles = Arrays.asList(context.getBundles());

        return bundles.subList(first, first + count).iterator();
    }

    public IModel<Bundle> model(Bundle object) {
        return new BundleModel(object.getBundleId(), context);
    }

    public int size() {
        return context.getBundles().length;
    }



}
