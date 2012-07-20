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
package org.apache.karaf.webconsole.karaf.feature.repository;

import java.net.URI;

import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.form.LabelBorder;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

/**
 * Repository add panel.
 */
public class AddRepositoryPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private TextField<URI> uri;

    public AddRepositoryPanel(String id, IModel<Repository> model) {
        super(id);

        Form<Repository> form = new Form<Repository>("add", new CompoundPropertyModel<Repository>(model));
        uri = new RequiredTextField<URI>("uri");
        uri.setRequired(true);
        uri.setLabel(new ResourceModel("feature.repository.uri"));

        LabelBorder border = new LabelBorder("border", uri);
        border.setHelp(new ResourceModel("feature.repository.uri.help"));
        form.add(border);
        add(form);
    }


}
