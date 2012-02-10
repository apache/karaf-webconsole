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
package org.apache.karaf.webconsole.core.navigation.markup;

import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Component responsible for rendering top navigation in webconsole.
 */
public class NavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public NavigationPanel(String id, IModel<List<ConsoleTabProvider>> model) {
        super(id);

        add(new ListView<ConsoleTabProvider>("tabs", model) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ConsoleTabProvider> item) {
                ConsoleTabProvider tab = item.getModelObject();

                item.add(tab.getModuleLink("moduleLink", "moduleLabel"));

                item.add(new ListView<Link<Page>>("moduleLinks", tab.getItems("link", "label")) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void populateItem(ListItem<Link<Page>> item) {
                        item.add(item.getModelObject());
                    }
                });
            }
        });

    }

}
