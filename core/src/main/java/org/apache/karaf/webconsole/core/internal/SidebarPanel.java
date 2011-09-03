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
package org.apache.karaf.webconsole.core.internal;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class SidebarPanel extends Panel {

    public SidebarPanel(String id, SidebarProvider provider) {
        super(id);

        add(provider.getMasterPageLink("masterPageLink", "masterPageLabel"));

        add(new ListView<Link<Page>>("subPageLinks", provider.getItems("subPageLink", "subPageLabel")) {
            @Override
            protected void populateItem(ListItem<Link<Page>> item) {
                item.add(item.getModelObject());
            }
        });

        add(new ListView<WidgetProvider>("widgets", provider.getWidgetProviders()) {
            @Override
            protected void populateItem(ListItem<WidgetProvider> item) {
                item.add(item.getModelObject().getWidgetPanel("widget"));
            }
        });
    }

}
