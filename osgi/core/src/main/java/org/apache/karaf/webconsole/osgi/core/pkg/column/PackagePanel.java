package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.osgi.core.pkg.PackagePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class PackagePanel extends Panel {

	@PaxWicketBean(name = "packageAdmin")
	private PackageAdmin admin;

	public PackagePanel(String componentId, IModel<Clause> model) {
		super(componentId);

		Clause clause = model.getObject();

		PageParameters params = new PageParameters();
		String pkg = clause.getName();
		params.add("package", pkg);

		String version = clause.getAttribute(Constants.VERSION_ATTRIBUTE);
		if (version != null) {
			params.add("version", version);
		} else {
			params.add("version", Version.emptyVersion.toString());
		}

		BookmarkablePageLink<PackagePage> link = new BookmarkablePageLink<PackagePage>("link", PackagePage.class, params);
		link.add(new Label("label", pkg));


		ExportedPackage exportedPackage = admin.getExportedPackage(pkg);
		if (exportedPackage == null) {
			link.add(new SimpleAttributeModifier("class", "error"));
		}

		add(link);
	}

}
