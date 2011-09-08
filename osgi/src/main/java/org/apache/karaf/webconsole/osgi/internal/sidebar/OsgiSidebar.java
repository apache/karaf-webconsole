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
package org.apache.karaf.webconsole.osgi.internal.sidebar;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.karaf.webconsole.osgi.internal.bundle.BundlesPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationsPage;
import org.apache.karaf.webconsole.osgi.internal.event.EventsPage;
import org.apache.karaf.webconsole.osgi.internal.log.LogsPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

/**
 * Generic OSGi sidebar.
 */
public class OsgiSidebar implements SidebarProvider {

    @SuppressWarnings("unchecked")
    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Arrays.asList(
            createPageLink(componentId, labelId, "Configuration", ConfigurationsPage.class),
            createPageLink(componentId, labelId, "Events", EventsPage.class),
            createPageLink(componentId, labelId, "Log entries", LogsPage.class)
        );
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return createPageLink(linkId, labelId, "Bundles", BundlesPage.class);
    }

    public List<WidgetProvider> getWidgetProviders() {
        return Collections.emptyList();
    }

}
