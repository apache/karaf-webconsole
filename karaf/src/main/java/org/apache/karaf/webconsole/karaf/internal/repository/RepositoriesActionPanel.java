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
package org.apache.karaf.webconsole.karaf.internal.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class RepositoriesActionPanel extends ActionsPanel<Repository> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public RepositoriesActionPanel(String componentId, IModel<Repository> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Repository object, String id) {
        List<Link> links = new ArrayList<Link>();

        Link remove = new Link(id) {
            @Override
            public void onClick() {
                Repository object = (Repository) RepositoriesActionPanel.this.getDefaultModelObject();

                featuresService.removeRepository(object.getURI());

                Session.get().info("Repository " + object.getURI() + " was removed");
            }
        };
        remove.add(new Label("label", "Remove"));

        links.add(remove);

        return links;
    }

}
