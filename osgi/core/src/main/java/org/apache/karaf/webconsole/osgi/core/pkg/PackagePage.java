package org.apache.karaf.webconsole.osgi.core.pkg;

import static org.apache.karaf.webconsole.osgi.core.bundle.SingleBundlePage.createLink;

import java.util.Arrays;

import org.apache.karaf.webconsole.osgi.core.bundle.SingleBundlePage;
import org.apache.karaf.webconsole.osgi.core.shared.BundleModel;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/package/detail")
public class PackagePage extends OsgiPage {

    @PaxWicketBean(name = "packageAdmin")
    private PackageAdmin admin;

    public PackagePage(PageParameters params) {
        String pkg = params.getString("package");
        String version = params.getString("version");

        add(new Label("package", pkg).setRenderBodyOnly(true));
        add(new Label("version", version).setRenderBodyOnly(true));

        ExportedPackage[] packages = admin.getExportedPackages((Bundle) null);

        boolean found = false;
        Version osgiVersion = new Version(version);
        for (ExportedPackage exportPkg : packages) {
            if (pkg.equals(exportPkg.getName()) && osgiVersion.equals(exportPkg.getVersion())) {
                found = true;
                populate(exportPkg);
                break;
            }
        }

        if (!found) {
            Link link = new Link("exporterLink") {
                @Override
                public void onClick() {
                    // TODO Auto-generated method stub
                    
                }
            };

            error("Unable to find package " + pkg);

            link.add(new Label("exporterLabel", "NOT FOUND"));
            add(link);
            add(new Label("packageDet"));
            add(new RepeatingView("importers"));
        }
    }

    private void populate(ExportedPackage exportPkg) {
        Bundle exporter = exportPkg.getExportingBundle();
        Bundle[] importers = exportPkg.getImportingBundles();

        PageParameters params = new PageParameters();
        params.put("bundleId", exporter.getBundleId());

        Link<SingleBundlePage> link = createLink("exporterLink", exporter);
        link.add(new Label("exporterLabel", new PropertyModel(new BundleModel(exporter), "symbolicName")));
        add(link);

        add(new Label("packageDet", exportPkg.getName()));

        add(new ListView<Bundle>("importers", Arrays.asList(importers)) {
            @Override
            protected void populateItem(ListItem<Bundle> item) {
                Bundle model = item.getModel().getObject();

                Link<SingleBundlePage> link = createLink("importerLink", model);
                link.add(new Label("importerLabel", model.getSymbolicName()));
                item.add(link);
            }
        });

    }
}
