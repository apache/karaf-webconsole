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
package org.apache.karaf.webconsole.karaf.admin.create;

import static org.apache.wicket.model.Model.of;

import org.apache.karaf.webconsole.karaf.admin.AdminPage;
import org.apache.karaf.webconsole.karaf.admin.model.WicketInstance;
import org.apache.karaf.webconsole.karaf.admin.model.WicketInstanceSettings;
import org.apache.karaf.webconsole.karaf.admin.settings.InstanceSettingsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Create instance page.
 */
@PaxWicketMountPoint(mountPoint = "/karaf/admin/create")
public class CreateInstancePage extends AdminPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")
    public CreateInstancePage() {
        WicketInstance instance = new WicketInstance();
        instance.setSettings(new WicketInstanceSettings());

        CompoundPropertyModel<WicketInstance> model = new CompoundPropertyModel<WicketInstance>(instance);

        Form<WicketInstance> form = new Form<WicketInstance>("instance", model);
        form.add(new TextField<String>("name"));
        form.add(new InstanceSettingsPanel("settings", of(new WicketInstanceSettings())));

        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                try {
                    WicketInstance instance = (WicketInstance) getForm().getModelObject();
                    admin.createInstance(instance.getName(), instance.getSettings());
                } catch (Exception e) {
                    e.printStackTrace();

                    Session.get().error("Cannot create instance");
                }
            }
        });

        add(form);
    }

}
