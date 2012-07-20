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
package org.apache.karaf.webconsole.osgi.core.bundle.install;

import static org.apache.wicket.model.Model.of;

import org.apache.karaf.webconsole.core.form.LabelBorder;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Bundle installation form wrapper.
 */
public class InstallBundlePanel extends Panel {

    private static final long serialVersionUID = 1L;
    private TextField<String> location;
    private CheckBox upload;
    private FileUploadField file;

    public InstallBundlePanel(String id, IModel<WicketInstallModel> model) {
        super(id);

        Form<WicketInstallModel> form = new Form<WicketInstallModel>("form", new CompoundPropertyModel<WicketInstallModel>(model));
        location = new RequiredTextField<String>("location");
        location.setLabel(of("Bundle location"));
        LabelBorder border = new LabelBorder("locationGroup", location);
        form.add(border);

        upload = new AjaxCheckBox("upload") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                if (target != null) {
                    if (upload.getModelObject()) {
                        onChecked();
                    } else {
                        onUnchecked();
                    }
                    target.add(file);
                }
            }
        };
        upload.setLabel(of("Specify file to install"));
        border = new LabelBorder("uploadGroup", upload);
        form.add(border);

        file = new FileUploadField("file");
        file.setLabel(of("Bundle file"));
        file.add(new AttributeModifier("disabled", "disabled"));
        file.setOutputMarkupId(true);
        border = new LabelBorder("fileGroup", file);
        form.add(border);

        add(form);
    }

    protected void onChecked() {
        file.add(AttributeModifier.remove("disabled"));
    }

    protected void onUnchecked() {
        file.add(new AttributeModifier("disabled", "disabled"));
    }
}
