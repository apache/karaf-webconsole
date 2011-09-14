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
package org.apache.karaf.webconsole.core.preferences;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;

/**
 * Base class to display preferences without edit possibility.
 */
public class PreferencesPanel extends Panel {

    public PreferencesPanel(String id, String user, PreferencesService service, IModel<Preferences> model) throws BackingStoreException {
        super(id, model);

        Preferences preferences = model.getObject();

        add(new Label("path", preferences.absolutePath()));

        RepeatingView repeatingView = new RepeatingView("preferences");
        for (String key : preferences.keys()) {
            WebMarkupContainer container = new WebMarkupContainer(repeatingView.newChildId());

            container.add(new Label("key", key));
            container.add(new Label("value", preferences.get(key, null)));
            repeatingView.add(container);
        }

        add(repeatingView);

        repeatingView = new RepeatingView("children");
        for (String child : preferences.childrenNames()) {
            WebMarkupContainer container = new WebMarkupContainer(repeatingView.newChildId());

            container.add(new PreferencesPanel("child", user, service, new PreferencesModel(service, preferences.node(child))));
        }
        add(repeatingView);
    }
}
