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
package org.apache.karaf.webconsole.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class BasePage2 extends WebPage {

    @PaxWicketBean(name = "tabs")
    private List<ConsoleTab> tabs;

    public BasePage2() {
        add(CSSPackageResource.getHeaderContribution(BasePage2.class, "style.css"));
        add(CSSPackageResource.getHeaderContribution(BasePage2.class, "grid.css"));

        add(new Label("footer", "Apache Karaf Console"));

        add(new Image("karafLogo", new ResourceReference(BasePage2.class, "images/karaf-logo.png")));

        add(new ListView<ConsoleTab>("tabs", tabs) {
            @Override
            protected void populateItem(ListItem<ConsoleTab> item) {
                final ConsoleTab tab = item.getModelObject();
                item.add(new PageLink("moduleLink", tab.getModuleHomePage()).add(new Label("moduleLabel", tab.getLabel())));

                List<String> subItems = new LinkedList<String>(tab.getItems().keySet());
                item.add(new ListView<String>("topLinks", subItems) {
                    @Override
                    protected void populateItem(ListItem<String> item) {
                        String subItem = item.getModelObject();
                        item.add(new PageLink("topLink", tab.getItems().get(subItem)).add(new Label("linkLabel", subItem)));
                    }
                });
            }
        });

        List tabPanels = new ArrayList();
        tabPanels.add(new AbstractTab(new Model("first tab")) {
            public Panel getPanel(String panelId) {
                return new TabPanel1(panelId);
            }
        });

        add(new TabbedPanel("tabPanels", tabPanels));

    }


    class TabPanel1 extends Panel {
        public TabPanel1(String id) {
            super(id);
        }
    }

}
