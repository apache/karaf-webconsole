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
package org.apache.karaf.webconsole.core.page;

import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.core.navigation.markup.NavigationPanel;
import org.apache.karaf.webconsole.core.preferences.PreferencesPage;
import org.apache.karaf.webconsole.core.security.WebConsoleSession;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.StringResourceModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.service.prefs.PreferencesService;

/**
 * Page which requires admin role, in other words authorized user.
 */
@AuthorizeInstantiation("admin")
public class SecuredPage extends BasePage {

    @PaxWicketBean(name = "tabs")
    private List<ConsoleTabProvider> tabs;

    @PaxWicketBean(name = "preferencesService")
    private PreferencesService preferences;

    public SecuredPage() {
        add(new NavigationPanel("navigationPanel", new LoadableDetachableModel<List<ConsoleTabProvider>>() {
            @Override
            protected List<ConsoleTabProvider> load() {
                return tabs;
            }
        }));

        String username = WebConsoleSession.get().getUsername();
        add(new AvatarImage("avatar", preferences.getUserPreferences(username)));

        add(new Label("username", username));
        add(new BookmarkablePageLink<PreferencesPage>("preferencesLink", PreferencesPage.class));

        Link<Void> aLink = new Link<Void>("logoutLink") {
            @Override
            public void onClick() {
                WebConsoleSession.get().invalidateNow();
                getRequestCycle().setRedirect(true);
                setResponsePage(LoginPage.class);
            }
        };
        aLink.add(new Label("logoutTranslatedLink",new StringResourceModel("logout.link", this.getDefaultModel())));
        add(aLink);

    }
}
