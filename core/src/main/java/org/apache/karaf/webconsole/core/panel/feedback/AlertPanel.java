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
package org.apache.karaf.webconsole.core.panel.feedback;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Bootstrap based alert panel.
 */
public final class AlertPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private WebMarkupContainer wrapper;

    public AlertPanel(String id, String message, AlertType type) {
        super(id);

        // make sure that wrapper is added before type of alert is set
        add(wrapper = new WebMarkupContainer("wrapper"));

        setType(type); // setter will modify css class

        wrapper.add(new Label("message", message));
    }

    public void setType(AlertType type) {
        wrapper.add(new AttributeAppender("class", Model.of(type.getCssClass()), " "));
    }

}