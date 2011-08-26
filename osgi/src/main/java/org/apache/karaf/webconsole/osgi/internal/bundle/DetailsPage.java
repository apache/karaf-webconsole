package org.apache.karaf.webconsole.osgi.internal.bundle;

import java.util.Arrays;
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
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

@PaxWicketMountPoint(mountPoint = "/osgi/bundle/detail")
public class DetailsPage extends BasePage {

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;
    private long bundleId;

    public DetailsPage(PageParameters params) {
        bundleId = params.getLong("bundleId");
        Bundle bundle = context.getBundle(bundleId);

        add(new Label("name", bundle.getSymbolicName()));

        String object = (String) bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
        if (object == null) object = "";

        add(new ListView<String>("imports", Arrays.asList(object.split(","))) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("importPackage", item.getModel()));
            }
        });

        object = (String) bundle.getHeaders().get(Constants.EXPORT_PACKAGE);
        if (object == null) object = "";
        add(new ListView<String>("exports", Arrays.asList(object.split(","))) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("exportPackage", item.getModel()));
            }
        });

        IModel<List<ServiceReference>> model = new LoadableDetachableModel<List<ServiceReference>>() {
            @Override
            protected List<ServiceReference> load() {
                return Arrays.asList(context.getBundle(bundleId).getServicesInUse());
            }
        };

        add(new ListView<ServiceReference>("servicesInUse", model) {
            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();
                item.add(new Label("serviceInUse", Arrays.toString((String[]) reference.getProperty("objectClass"))));
            }
        });

        model = new LoadableDetachableModel<List<ServiceReference>>() {
            @Override
            protected List<ServiceReference> load() {
                return Arrays.asList(context.getBundle(bundleId).getRegisteredServices());
            }
        };

        add(new ListView<ServiceReference>("servicesExported", model) {
            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();
                item.add(new Label("serviceExported", Arrays.toString((String[]) reference.getProperty("objectClass"))));
            }
        });
    }


}
