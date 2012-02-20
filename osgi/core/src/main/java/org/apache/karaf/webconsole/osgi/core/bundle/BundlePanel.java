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
package org.apache.karaf.webconsole.osgi.core.bundle;

import org.apache.karaf.webconsole.osgi.core.shared.State;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.osgi.framework.Bundle;

public class BundlePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public BundlePanel(String id, IModel<Bundle> model) {
        this(id, model, false);
    }

    public BundlePanel(String id, IModel<Bundle> model, boolean renderLink) {
        super(id, new CompoundPropertyModel<Bundle>(model));

        Bundle bundle = model.getObject();
        add(new Label("bundleId"));
        add(new Label("state", State.of(bundle.getState()).toString()));
        add(new Label("symbolicName"));
        add(new Label("version", new PropertyModel<Bundle>(model, "version.toString")));
        add(new Label("location"));

        Link<SingleBundlePage> link = SingleBundlePage.createLink("link", bundle);
        link.setVisible(renderLink);
        add(link);
    }

}
