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
import org.apache.karaf.webconsole.osgi.core.service.ExportServiceTable;
import org.apache.karaf.webconsole.osgi.core.service.ImportServiceTable;
import org.apache.karaf.webconsole.osgi.core.shared.BundleModel;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;

@PaxWicketMountPoint(mountPoint = "/osgi/bundle/detail")
public class SingleBundlePage extends OsgiPage {

    public static String BUNDLE_ID = "bundleId";

    private long bundleId;

    public SingleBundlePage(PageParameters params) throws IOException {
        bundleId = params.get("bundleId").toLong();
        Bundle bundle = context.getBundle(bundleId);

        BundleModel model = new BundleModel(bundle);
        setDefaultModel(new CompoundPropertyModel<Bundle>(model));
        add(new BundlePanel("bundle", model));

        add(new Label("symbolicName").setRenderBodyOnly(true));
        add(new Label("version").setRenderBodyOnly(true));

        add(new ImportPackageTable("imports", bundle));
        add(new ExportPackageTable("exports", bundle));
        add(new ImportServiceTable("serviceImports", bundle));
        add(new ExportServiceTable("serviceExports", bundle));
    }

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
        params.add(BUNDLE_ID, bundleId);
        return new SecuredPageLink<SingleBundlePage>(id, SingleBundlePage.class, params);
    }

}
