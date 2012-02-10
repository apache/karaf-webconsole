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
package org.apache.karaf.webconsole.core.internal.preferences;

import org.apache.karaf.webconsole.core.page.AvatarImage;
import org.apache.karaf.webconsole.core.preferences.PreferencesModel;
import org.apache.karaf.webconsole.core.security.WebConsoleSession;
import org.apache.wicket.markup.html.panel.Panel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;

/**
 * Preferences edit panel.
 */
public class SystemPreferencesPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "preferencesService")
    private PreferencesService preferences;

    public SystemPreferencesPanel(String id) {
        super(id);

        String user = WebConsoleSession.get().getUsername();

        Preferences userPreferences = preferences.getUserPreferences(user);

        add(new AvatarImage("currentAvatar", userPreferences));

        PreferencesModel formModel = new PreferencesModel(preferences, userPreferences, user);
        add(new SystemPreferencesForm("systemPreferences", formModel));
    }

}
