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
package org.apache.karaf.webconsole.osgi.blueprint;

import java.util.List;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.osgi.blueprint.view.BlueprintDataTable;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.ServiceReference;

/**
 * Blueprint list page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/blueprint")
public class BlueprintPage extends SinglePage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "containers")
    private List<ServiceReference> containers;

    public BlueprintPage() {
        add(new BlueprintDataTable("containers", new BlueprintDataProvider(containers), 100));

        /*
        add(new ListView<ServiceReference>("containers", new ListModel<ServiceReference>(containers)) {

            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();

                String symbolicName = (String) reference.getProperty("osgi.blueprint.container.symbolicname");
                Version version = (Version) reference.getProperty("osgi.blueprint.container.version");

                item.add(new Label("symbolicName", symbolicName));
                item.add(new Label("version", version.toString()));

                PageParameters params = new PageParameters();
                params.put("bundleId", reference.getBundle().getBundleId());
                item.add(new BookmarkablePageLink<SingleBundlePage>("details", SingleBundlePage.class, params));
            }
            
        });
        */

    }

}
