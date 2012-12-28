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
import java.util.Locale;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.core.page.AvatarImage;
import org.apache.karaf.webconsole.core.preferences.PreferencesPage;
import org.apache.karaf.webconsole.core.security.SecuredPageLink;
import org.apache.karaf.webconsole.core.security.WebConsoleSession;
import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.service.prefs.PreferencesService;

/**
 * Panel which contains links to all active modules, without its children.
 */
public class NavigationTopPanel extends LanguageTopPanel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "preferencesService")
    private PreferencesService preferences;

    @PaxWicketBean(name = "tabs")
    protected List<ConsoleTabProvider> tabs;

    public NavigationTopPanel(String id, IModel<List<Locale>> locales) {
        super(id, locales);

        add(createTabList());

        String username = WebConsoleSession.get().getUsername();
        add(new AvatarImage("avatar", preferences.getUserPreferences(username)));

        add(new Label("username", username));
        add(new SecuredPageLink<PreferencesPage>("preferencesLink", PreferencesPage.class));

        add(new LogoutLink("logoutLink"));
    }

    protected Component createTabList() {
        return new ListView<ConsoleTabProvider>("tabs", tabs) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ConsoleTabProvider> item) {
                Link<Page> link = item.getModelObject().getModuleLink("moduleLink", "moduleLabel");
                item.add(link);

                if (LinkUtils.isActiveTrail(link)) {
                    item.add(AttributeModifier.append("class", "active"));
                }
            }
        };
    }


}
