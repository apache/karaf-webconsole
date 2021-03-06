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
package org.apache.karaf.webconsole.osgi.scr.link;

import org.apache.felix.scr.Component;
import org.apache.felix.scr.ScrService;
import org.apache.karaf.webconsole.osgi.scr.ScrComponent;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public abstract class ScrLink extends Link<Bundle> {

    private static final long serialVersionUID = 1L;

    private String component;

    public ScrLink(String id, IModel<Bundle> model, String componentName) {
        super(id, model);

        this.component = componentName;
    }

    @Override
    public void onClick() {
        ScrService scr = ScrComponent.getScrService();
        if (scr == null) {
            Session.get().warn("SCR service not found");
            return;
        }

        Component[] components = scr.getComponents(getModelObject());
        if (components == null) {
            Session.get().warn("SCR components for bundle " + getModelObject().getSymbolicName() + " not found");
            return;
        }

        for (Component component : components) {
            if (this.component == component.getName()) {
                onClick(component);
                return;
            }
        }
        Session.get().warn("SCR component named " + component + " not found in bundle " + getModelObject().getSymbolicName());
    }

    protected abstract void onClick(Component component);

}
