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
package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public class DecorationPanel extends Panel {

    public DecorationPanel(String id, IModel<Bundle> model, List<IDecorationProvider> decorationProviders) {
        super(id, model);

        add(CSSPackageResource.getHeaderContribution(DecorationPanel.class, "decoration.css"));

        List<Component> components = new ArrayList<Component>();
        for (IDecorationProvider provider : decorationProviders) {
            Component decoration = provider.getDecoration("extension", model);
            if (decoration != null) {
                components.add(decoration);
            }
        }

        add(new ListView<Component>("extensions", components) {
            @Override
            protected void populateItem(ListItem<Component> item) {
                item.add(item.getModelObject());
            }
        });

    }

}
