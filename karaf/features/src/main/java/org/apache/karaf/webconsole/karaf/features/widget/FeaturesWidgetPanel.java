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
package org.apache.karaf.webconsole.karaf.features.widget;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.karaf.features.feature.FeaturesPage;
import org.apache.karaf.webconsole.karaf.features.repository.RepositoriesPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class FeaturesWidgetPanel extends Panel {

    public FeaturesWidgetPanel(String id, FeaturesService service) {
        super(id);

        try {
            Feature[] allFeatures = service.listFeatures();
            Feature[] installed = service.listInstalledFeatures();

            add(new Label("featuresCount", "" + allFeatures.length));
            add(new Label("installedCount", "" + installed.length));
            add(new Label("uninstalledCount", "" + (allFeatures.length - installed.length)));

            add(new BookmarkablePageLink<FeaturesPage>("featuresLink", FeaturesPage.class));
            add(new BookmarkablePageLink<RepositoriesPage>("repositoriesLink", RepositoriesPage.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
