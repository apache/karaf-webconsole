/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.osgi.core.pkg;

import java.util.Arrays;

import org.apache.karaf.webconsole.osgi.core.bundle.BundlePanel;
import org.apache.karaf.webconsole.osgi.core.shared.BundleModel;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/package/detail")
public class SinglePackagePage extends OsgiPage {

    @PaxWicketBean(name = "packageAdmin")
    private PackageAdmin admin;

    public SinglePackagePage(PageParameters params) {
        String pkg = params.get("package").toString();
        String version = params.get("version").toString();

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
            throw new IllegalArgumentException("Package " + pkg + " " + version + " not found");
        }
    }

    private void populate(ExportedPackage exportPkg) {
        Bundle exporter = exportPkg.getExportingBundle();
        Bundle[] importers = exportPkg.getImportingBundles();

        add(new BundlePanel("exporter", new BundleModel(exporter)));

        add(new Label("packageDet", exportPkg.getName()));

        add(new ListView<Bundle>("importers", Arrays.asList(importers)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Bundle> item) {
                BundlePanel panel = new BundlePanel("importer", item.getModel(), true);
                item.add(panel);
            }
        });

    }
}
