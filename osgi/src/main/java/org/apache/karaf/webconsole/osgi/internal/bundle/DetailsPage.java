package org.apache.karaf.webconsole.osgi.internal.bundle;

import java.util.Arrays;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

public class DetailsPage extends BasePage {

	@PaxWicketBean(name = "blueprintBundleContext")
	private BundleContext context;

	public DetailsPage(PageParameters params) {
		long bundleId = params.getLong("bundleId");
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

		ServiceReference[] servicesInUse = bundle.getServicesInUse();
		if (servicesInUse == null) {
			servicesInUse = new ServiceReference[0];
		}

		add(new ListView<ServiceReference>("servicesInUse", Arrays.asList(servicesInUse)) {

			@Override
			protected void populateItem(ListItem<ServiceReference> item) {
				final ServiceReference reference = item.getModelObject();
				item.add(new Label("serviceInUse", Arrays.toString((String[]) reference.getProperty("objectClass"))));

				item.add(new ListView<String>("serviceInUseProperty", Arrays.asList(reference.getPropertyKeys())) {
					@Override
					protected void populateItem(ListItem<String> item) {
						item.add(new Label("propertyName", item.getModelObject()));
						Object property = reference.getProperty(item.getModelObject());
						if (property instanceof Object[]) {
							property = Arrays.toString((Object[]) property);
						}
						item.add(new Label("propertyValue", "" + property));
					}
				});
				
			}
			
		});
	}


}
