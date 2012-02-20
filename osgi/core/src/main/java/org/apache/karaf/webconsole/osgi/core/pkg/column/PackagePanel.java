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
package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.osgi.core.pkg.SinglePackagePage;
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

    private static final long serialVersionUID = 1L;

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

        BookmarkablePageLink<SinglePackagePage> link = new BookmarkablePageLink<SinglePackagePage>("link", SinglePackagePage.class, params);
        link.add(new Label("label", pkg));


        ExportedPackage exportedPackage = admin.getExportedPackage(pkg);
        if (exportedPackage == null) {
            link.add(new SimpleAttributeModifier("class", "error"));
        }

        add(link);
    }

}
