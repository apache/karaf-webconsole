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
package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.bundle.IActionProvider;
import org.apache.karaf.webconsole.osgi.internal.bundle.DetailsPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public class BundleActionsPanel extends ActionsPanel<Bundle> {

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
    protected List<Link> getLinks(Bundle object, String id) {
        PageParameters params = new PageParameters();
        params.put("bundleId", object.getBundleId());

        List<Link> links = new ArrayList<Link>();

        // details link
        Link link = new BookmarkablePageLink<DetailsPage>(id, DetailsPage.class, params);
        link.add(new Label("label", "Details"));

        links.add(link);

        return links;
    }
}
