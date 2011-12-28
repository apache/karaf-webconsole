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
package org.apache.karaf.webconsole.osgi.bundle.internal.view;

import org.apache.karaf.webconsole.core.panel.CssImagePanel;
import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.service.startlevel.StartLevel;

public class SystemBundleDecorationProvider implements IDecorationProvider {

    private StartLevel startLevel;

    public SystemBundleDecorationProvider(StartLevel startLevel) {
        this.startLevel = startLevel;
    }

    public Panel getDecoration(String componentId, IModel<Bundle> model) {
        Bundle bundle = model.getObject();

        int bundleStartLevel = startLevel.getBundleStartLevel(bundle);
        if (bundleStartLevel < startLevel.getInitialBundleStartLevel()) {
            return new CssImagePanel(componentId, "system");
        }

        return null;
    }
}