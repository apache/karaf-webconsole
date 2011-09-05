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
package org.apache.karaf.webconsole.core.navigation;

import java.util.List;

import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

/**
 * Sidebar provider is responsible for enriching sidebar, it have a master page
 * link and set od widget providers.
 */
public interface SidebarProvider extends NavigationProvider {

    /**
     * Gets master page link - default page which should be on top of all.
     * 
     * @param linkId Link element id.
     * @param labelId Label element id.
     * @return Master page link.
     */
    Link<Page> getMasterPageLink(String linkId, String labelId);

    /**
     * Gets widget providers for given sidebar.
     * 
     * @return
     */
    List<WidgetProvider> getWidgetProviders();

}
