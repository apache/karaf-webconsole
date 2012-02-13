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

import org.apache.karaf.webconsole.osgi.core.bundle.list.BundlePage;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bundle installation link. Requires form with {@link WicketInstallModel}.
 */
public class InstallBundleSubmitLink extends SubmitLink {
    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;

    private transient Logger logger = LoggerFactory.getLogger(InstallBundleSubmitLink.class);

    public InstallBundleSubmitLink(String id) {
        super(id);
    }

    @Override
    public void onSubmit() {
        WicketInstallModel modelObject = (WicketInstallModel) getForm().getModelObject();

        try {
            if (modelObject.isUpload()) {
                context.installBundle(modelObject.getLocation(), modelObject.getInputStream());
            } else {
                context.installBundle(modelObject.getLocation());
            }
            Session.get().info("Bundle " + modelObject.getLocation() + " installed");
            RequestCycle.get().setResponsePage(BundlePage.class);
        } catch (Exception e) {
            logger.error("Error during installation", e);
            Session.get().error("Can not install bundle " + e.getMessage());
        }
    }
}
