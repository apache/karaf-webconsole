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

import java.util.List;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.panel.PanelProvider;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Page used to display preferences from various bundles.
 */
@PaxWicketMountPoint(mountPoint = "/preferences")
public class PreferencesPage extends SinglePage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "preferencesProviders")
    private List<PanelProvider> providers;

    public PreferencesPage() throws BackingStoreException {
        RepeatingView preferencesView = new RepeatingView("preferences");

        for (PanelProvider provider : providers) {
            MarkupContainer container = new WebMarkupContainer(preferencesView.newChildId());
            container.add(provider.createPanel("preferencesPanel"));
            preferencesView.add(container);
        }

        add(preferencesView);
    }

}
