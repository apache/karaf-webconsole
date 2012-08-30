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

import org.apache.karaf.webconsole.osgi.blueprint.BlueprintState;
import org.apache.karaf.webconsole.osgi.blueprint.IBlueprintBundleStateTracker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;

public class BlueprintPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "tracker")
    private IBlueprintBundleStateTracker tracker;

    public BlueprintPanel(String componentId, IModel<Bundle> model) {
        super(componentId, model);

        Bundle bundle = model.getObject();
        BlueprintState state = tracker.getState(bundle);
        if (state != null) {
            add(new Label("state", state.name()));
        } else {
            add(new Label("state"));
        }
    }

}
