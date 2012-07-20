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

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Panel which renders tabs to active module children. It is not extension of
 * NavigationTopPanel, it's separate piece of code used in SecuredPage.
 */
public class ModuleTabPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "tabs")
    protected List<ConsoleTabProvider> tabs;

    @SuppressWarnings("serial")
    public ModuleTabPanel(String id) {
        super(id);

        IModel<List<Link<Page>>> links = new LoadableDetachableModel<List<Link<Page>>>() {
            @Override
            protected List<Link<Page>> load() {
                if (tabs == null) {
                    return new ArrayList<Link<Page>>();
                }
                for (ConsoleTabProvider provider : tabs) {
                    Link<Page> moduleLink = provider.getModuleLink("moduleLink", "moduleLabel");
                    if (LinkUtils.isActiveTrail(moduleLink)) {
                        return provider.getItems("link", "label");
                    }
                }
                return new ArrayList<Link<Page>>();
            }
        };

        add(new ListView<Link<Page>>("moduleLinks", links) {
            @Override
            protected void populateItem(ListItem<Link<Page>> item) {
                Link<Page> link = item.getModelObject();
                item.add(link);
                if (LinkUtils.isActiveTrail(link)) {
                    item.add(new AttributeModifier("class", "active"));
                }
            }
        });
    }

}
