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
package org.apache.karaf.webconsole.osgi.blueprint.bundle;

import org.apache.karaf.webconsole.core.panel.SingleLinkPanel;
import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.karaf.webconsole.osgi.blueprint.IBlueprintBundleStateTracker;
import org.apache.karaf.webconsole.osgi.blueprint.details.DetailsPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;

public class BlueprintActionPanel extends SingleLinkPanel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "tracker")
    private IBlueprintBundleStateTracker tracker;

    public BlueprintActionPanel(String componentId, IModel<Bundle> model) {
        super(componentId);
        setDefaultModel(model);

        Bundle bundle = model.getObject();
        if (tracker.getState(model.getObject()) == null) {
            setVisible(false);
            return;
        }

        PageParameters params = new PageParameters();
        params.add("bundleId", bundle.getBundleId());
        add(LinkUtils.createPageLink("link", "label", "Manage blueprint", DetailsPage.class, params));
    }

}
