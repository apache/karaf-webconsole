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
package org.apache.karaf.webconsole.core.form;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class LabelBorder extends Border {

    private static final long serialVersionUID = 1L;
    private final FormComponent<?> component;

    private Label help;

    public LabelBorder(String id, FormComponent<?> component) {
        super(id);
        this.component = component;

        add(component);

        addToBorder(
            new SimpleFormComponentLabel("label", component),
            help = new Label("help", ""),
            new ComponentFeedbackPanel("error", component)
        );
    }

    public void setHelp(String message) {
        help.setDefaultModelObject(message);
    }

    public void setHelp(IModel<?> message) {
        help.setDefaultModel(message);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        if (component.getFeedbackMessages() != null) {
            add(new AttributeAppender("class", Model.of("error"), " "));
        }
    }
}
