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
import org.apache.karaf.webconsole.osgi.scr.link.DisableLink;
import org.apache.karaf.webconsole.osgi.scr.link.EnableLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public class ScrActionPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private RepeatingView view;
    private int childCount = 0;

    public ScrActionPanel(String componentId, IModel<Bundle> model, Component[] components) {
        super(componentId, model);

        view = new RepeatingView("components");
        for (Component component : components) {
            switch (component.getState()) {
            case Component.STATE_ACTIVE:
                view.add(createDisableLink(component, view.newChildId()));
                childCount++;
                break;
            case Component.STATE_DISABLED:
                view.add(createEnableLink(component, view.newChildId()));
                childCount++;
                break;
            }
        }

        add(view);
    }

    private WebMarkupContainer createDisableLink(Component component, String childId) {
        WebMarkupContainer container = new WebMarkupContainer(childId);
        Link<Bundle> link = new DisableLink("link", getModel(), component.getName());
        link.add(new Label("label", "Disable " + component.getName()));
        container.add(link);
        return container;
    }

    private WebMarkupContainer createEnableLink(Component component, String childId) {
        WebMarkupContainer container = new WebMarkupContainer(childId);
        Link<Bundle> link = new EnableLink("link", getModel(), component.getName());
        link.add(new Label("label", "Enable " + component.getName()));
        container.add(link);
        return container;
    }

    @SuppressWarnings("unchecked")
    public IModel<Bundle> getModel() {
        return (IModel<Bundle>) getDefaultModel();
    }

    @Override
    public boolean isVisible() {
        return childCount > 0;
    }

}
