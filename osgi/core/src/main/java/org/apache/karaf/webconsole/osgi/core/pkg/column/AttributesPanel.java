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
package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Attribute;
import org.apache.felix.utils.manifest.Clause;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Constants;

public class AttributesPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AttributesPanel(String componentId, IModel<Clause> model) {
        super(componentId);
        RepeatingView repeatingView = new RepeatingView("attributes");

        Attribute[] attributes = model.getObject().getAttributes();
        for (Attribute attribute : attributes) {
            // skip version attribute
            if (Constants.VERSION_ATTRIBUTE.equals(attribute.getName())) {
                continue;
            }
            WebMarkupContainer container = new WebMarkupContainer(repeatingView.newChildId());

            container.add(new Label("name", attribute.getName()));
            container.add(new Label("value", attribute.getValue()));
            repeatingView.add(container);
        }
        add(repeatingView);
    }

}
