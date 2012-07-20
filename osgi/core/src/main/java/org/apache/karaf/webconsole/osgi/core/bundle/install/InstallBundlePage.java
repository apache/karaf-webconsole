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
package org.apache.karaf.webconsole.osgi.core.bundle.install;

import static org.apache.wicket.model.Model.of;

import org.apache.karaf.webconsole.osgi.core.bundle.list.BundlePage;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Page with bundle installation form.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/bundle/add")
public class InstallBundlePage extends OsgiPage {

    private static final long serialVersionUID = 1L;

    public InstallBundlePage() {
        IModel<WicketInstallModel> model = of(new WicketInstallModel());

        Form<WicketInstallModel> form = new Form<WicketInstallModel>("install", model);
        form.add(new InstallBundlePanel("bundle", model));

        form.add(new InstallBundleSubmitLink("confirm"));
        form.add(new BookmarkablePageLink<BundlePage>("cancel", BundlePage.class));

        add(form);
    }

}
