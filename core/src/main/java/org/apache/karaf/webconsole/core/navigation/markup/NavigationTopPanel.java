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
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.service.prefs.PreferencesService;

/**
 * Component responsible for rendering top navigation in webconsole.
 */
public class NavigationTopPanel extends LanguageTopPanel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "preferencesService")
    private PreferencesService preferences;

    public NavigationTopPanel(String id, IModel<List<Locale>> locales, IModel<List<ConsoleTabProvider>> model) {
        super(id, locales);

        add(createTabList(model));

        String username = WebConsoleSession.get().getUsername();
        add(new AvatarImage("avatar", preferences.getUserPreferences(username)));

        add(new Label("username", username));
        add(new SecuredPageLink<PreferencesPage>("preferencesLink", PreferencesPage.class));

        Link<Void> aLink = new LogoutLink("logoutLink");
        aLink.add(new Label("label", new StringResourceModel("logout.link", this.getDefaultModel())));
        add(aLink);

    }

    private Component createTabList(IModel<List<ConsoleTabProvider>> model) {
        return new ListView<ConsoleTabProvider>("tabs", model) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ConsoleTabProvider> item) {
                ConsoleTabProvider tab = item.getModelObject();

                List<Link<Page>> items = tab.getItems("link", "label");
                if (!items.isEmpty()) {
                    populateTabItem(item, tab);
                    populateTabChildren(item, items);
                } else {
                    populateSingleTabItem(item, tab);
                }
            }
        };
    }

    protected void populateTabItem(ListItem<ConsoleTabProvider> item, ConsoleTabProvider provider) {
        Link<Page> link = provider.getModuleLink("moduleLink", "moduleLabel");
        item.add(link);

        if (LinkUtils.isActiveTrail(link, this)) {
            item.add(new AttributeAppender("class", Model.of("active"), " "));
        }
    }

    protected void populateSingleTabItem(ListItem<ConsoleTabProvider> item, ConsoleTabProvider provider) {
        Link<Page> moduleLink = provider.getModuleLink("moduleLink", "moduleLabel");
        
        // remove dropdown stuff
        item.add(new SimpleAttributeModifier("class", ""));
        moduleLink.add(new SimpleAttributeModifier("data-toggle", ""));
        moduleLink.add(new SimpleAttributeModifier("class", ""));

        if (LinkUtils.isActiveTrail(moduleLink, this)) {
            item.add(new AttributeAppender("class", Model.of("active"), " "));
        }

        item.add(moduleLink);
        item.add(new RepeatingView("moduleLinks"));
    }

    protected void populateTabChildren(ListItem<ConsoleTabProvider> item, List<Link<Page>> listItems) {
        item.add(new ListView<Link<Page>>("moduleLinks", listItems) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Link<Page>> item) {
                item.add(item.getModelObject());
            }
        });
    }
}
