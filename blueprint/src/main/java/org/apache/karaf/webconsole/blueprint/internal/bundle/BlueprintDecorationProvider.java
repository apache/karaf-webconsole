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
package org.apache.karaf.webconsole.blueprint.internal.bundle;

import org.apache.karaf.webconsole.blueprint.internal.IBlueprintBundleStateTracker;
import org.apache.karaf.webconsole.core.panel.StaticImagePanel;
import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * Implementation of decoration provider which decorates bundles list view by
 * adding blueprint image.
 */
public class BlueprintDecorationProvider implements IDecorationProvider {

    private IBlueprintBundleStateTracker tracker;

    public BlueprintDecorationProvider(IBlueprintBundleStateTracker tracker) {
        this.tracker = tracker;
    }

    public Panel getDecoration(final String componentId, IModel<Bundle> model) {
        if (tracker.getState(model.getObject()) != null) {
            return new StaticImagePanel(componentId, new ResourceReference(getClass(), "blueprint.gif"));
        }
        return null;
    }

}
