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
package org.apache.karaf.webconsole.osgi.core.bundle;

import java.io.IOException;

import org.apache.karaf.webconsole.core.security.SecuredPageLink;
import org.apache.karaf.webconsole.osgi.core.pkg.ExportPackageTable;
import org.apache.karaf.webconsole.osgi.core.pkg.ImportPackageTable;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;

@PaxWicketMountPoint(mountPoint = "/osgi/bundle/detail")
public class SingleBundlePage extends OsgiPage {

    public static String BUNDLE_ID = "bundleId";

    private long bundleId;

    public SingleBundlePage(PageParameters params) throws IOException {
        bundleId = params.getLong("bundleId");
        Bundle bundle = context.getBundle(bundleId);

        //ExportedPackage[] exported = admin.getExportedPackages(bundle);

        add(new Label("name", bundle.getSymbolicName()).setRenderBodyOnly(true));

//        String object = (String) bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
//        if (object == null) object = "";
        add(new ImportPackageTable("imports", bundle));

//        add(new ListView<Package>("imports", parser.getImportPackages()) {
//            @Override
//            protected void populateItem(ListItem<Package> item) {
//                Package model = item.getModelObject();
//                String value = model.getName();
//                item.add(new Label("importPackage", value));
//            }
//        });

        add(new ExportPackageTable("exports", bundle));

//        IModel<List<ServiceReference>> model = new LoadableDetachableModel<List<ServiceReference>>() {
//            @Override
//            protected List<ServiceReference> load() {
//                return Arrays.asList(context.getBundle(bundleId).getServicesInUse());
//            }
//        };
//
//        add(new ListView<ServiceReference>("servicesIn", model) {
//            @Override
//            protected void populateItem(ListItem<ServiceReference> item) {
//                ServiceReference reference = item.getModelObject();
//                item.add(new Label("serviceInUse", Arrays.toString((String[]) reference.getProperty("objectClass"))));
//            }
//        });
//
//        model = new LoadableDetachableModel<List<ServiceReference>>() {
//            @Override
//            protected List<ServiceReference> load() {
//                return Arrays.asList(context.getBundle(bundleId).getRegisteredServices());
//            }
//        };
//
//        add(new ListView<ServiceReference>("servicesOut", model) {
//            @Override
//            protected void populateItem(ListItem<ServiceReference> item) {
//                ServiceReference reference = item.getModelObject();
//                item.add(new Label("serviceExported", Arrays.toString((String[]) reference.getProperty("objectClass"))));
//            }
//        });
    }

//    public static void main(String[] args) throws IOException {
//        Manifest manifest = new Manifest(new FileInputStream("target/classes/META-INF/MANIFEST.MF"));
//        Attributes mainAttributes = manifest.getMainAttributes();
//
//        Map<String, Map<String, String>> keySet = OSGiHeader.parseHeader(manifest.getMainAttributes().getValue(Constants.IMPORT_PACKAGE));
//        System.out.println(keySet);
//
////        for (Entry<Object, Object> string : mainAttributes.entrySet()) {
////            System.out.println(OSGiHeader.parseHeader("" + string.getValue()));
////        }
//    }

    /**
     * Create link to page with given bundle.
     * 
     * @param id Link id.
     * @param bundle Bundle.
     * @return Link to page.
     */
    public static Link<SingleBundlePage> createLink(String id, Bundle bundle) {
        return createLink(id, bundle.getBundleId());
    }

    /**
     * Create link to page with given bundle id..
     * 
     * @param id Link id.
     * @param bundleId Bundle identifier.
     * @return Link to page.
     */
    public static Link<SingleBundlePage> createLink(String id, long bundleId) {
        PageParameters params = new PageParameters();
        params.put(BUNDLE_ID, bundleId);
        return new SecuredPageLink<SingleBundlePage>(id, SingleBundlePage.class, params);
    }

}
