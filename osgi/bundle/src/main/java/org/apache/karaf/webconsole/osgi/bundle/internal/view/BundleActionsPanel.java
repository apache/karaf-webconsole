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
package org.apache.karaf.webconsole.osgi.bundle.internal.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.bundle.IActionProvider;
import org.apache.karaf.webconsole.osgi.bundle.internal.BundlesPage;
import org.apache.karaf.webconsole.osgi.bundle.internal.DetailsPage;
import org.apache.karaf.webconsole.osgi.bundle.internal.State;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;

/**
 * Action panel for bundles list.
 */
@SuppressWarnings("rawtypes")
public class BundleActionsPanel extends ActionsPanel<Bundle> {

    @PaxWicketBean(name = "packageAdmin")
    private PackageAdmin admin;

    public BundleActionsPanel(String componentId, final IModel<Bundle> model, List<IActionProvider> actionProviders) {
        super(componentId, model);

        /*
        add(new ListView<IActionProvider>("extensions", new ListModel<IActionProvider>(actionProviders)) {
            @Override
            protected void populateItem(ListItem<IActionProvider> item) {
                item.add(item.getModelObject().create("extension", model.getObject()));
            }
        });
        */
    }

    @Override
    protected List<Link> getLinks(Bundle object, String linkId, String labelId) {
        PageParameters params = new PageParameters();
        params.put("bundleId", object.getBundleId());

        List<Link> links = new ArrayList<Link>();

        // details link
        Link link = new BookmarkablePageLink<DetailsPage>(linkId, DetailsPage.class, params);
        link.add(new Label("label", "Details"));

        links.add(link);

        switch (State.of(object.getState())) {
        case ACTIVE:
            links.add(createStopLink(linkId, labelId));
            break;
        case INSTALLED:
        case RESOLVED:
            links.add(createStartLink(linkId, labelId));
        }

        links.add(createRefreshLink(linkId, labelId));
        links.add(createUninstallLink(linkId, labelId));

        return links;
    }

    private Link createUninstallLink(String linkId, String labelId) {
        Link link = new Link(linkId) {
            @Override
            public void onClick() {
                Bundle bundle = (Bundle) BundleActionsPanel.this.getDefaultModelObject();

                try {
                    bundle.uninstall();

                    Session.get().info("Bundle " + bundle.getSymbolicName() + " uninstalled");
                    RequestCycle.get().setResponsePage(BundlesPage.class);
                } catch (BundleException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        };
        link.add(new Label(labelId, "Uninstall"));
        return link;
    }

    private Link createRefreshLink(String linkId, String labelId) {
        Link link = new Link(linkId) {
            @Override
            public void onClick() {
                Bundle bundle = (Bundle) BundleActionsPanel.this.getDefaultModelObject();

                admin.refreshPackages(new Bundle[] {bundle});
                Session.get().info("Bundle " + bundle.getSymbolicName() + " refreshed");
                RequestCycle.get().setResponsePage(BundlesPage.class);
            }
            
        };
        link.add(new Label(labelId, "Refresh"));
        return link;
    }

    private Link createStartLink(String linkId, String labelId) {
        Link link = new Link(linkId) {
            @Override
            public void onClick() {
                Bundle bundle = (Bundle) BundleActionsPanel.this.getDefaultModelObject();

                try {
                    bundle.start();
                    Session.get().info("Bundle " + bundle.getSymbolicName() + " started");
                    RequestCycle.get().setResponsePage(BundlesPage.class);
                } catch (BundleException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        };
        link.add(new Label(labelId, "Start"));
        return link;
    }

    private Link createStopLink(String linkId, String labelId) {
        Link link = new Link(linkId) {
            public void onClick() {
                Bundle bundle = (Bundle) BundleActionsPanel.this.getDefaultModelObject();
                try {
                    bundle.stop();
                    Session.get().info("Bundle " + bundle.getSymbolicName() + " stopped");
                    RequestCycle.get().setResponsePage(BundlesPage.class);
                } catch (BundleException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        link.add(new Label(labelId, "Stop"));
        return link;
    }
}
