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
package org.apache.karaf.webconsole.osgi.scr;

import org.apache.felix.scr.Component;
import org.apache.felix.scr.ScrService;
import org.apache.karaf.webconsole.osgi.core.shared.BundleModel;
import org.apache.karaf.webconsole.osgi.core.spi.IActionProvider;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.osgi.framework.Bundle;

public class ScrActionProvider implements IActionProvider {

    public Panel create(String componentId, Bundle object) {
        ScrService scr = ScrComponent.getScrService();
        if (scr == null) {
            return null;
        }

        Component[] components = scr.getComponents(object);
        if (components != null) {
            return new ScrActionPanel(componentId, new BundleModel(object), components);
        }

        EmptyPanel panel = new EmptyPanel(componentId);
        panel.setVisible(false);
        return panel;
    }

}
