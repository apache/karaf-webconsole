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
package org.apache.karaf.webconsole.osgi.config.view;

import java.io.IOException;

import org.apache.karaf.webconsole.osgi.config.ConfigurationsPage;
import org.apache.karaf.webconsole.osgi.config.model.ConfigurationNotFoundException;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmitter;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

/**
 * Simple form responsible for removal of the configuration.
 */
public class ConfigurationRemoveForm extends Form<Configuration> {

    private static final long serialVersionUID = 1L;

    public ConfigurationRemoveForm(String id, IModel<Configuration> model) {
        super(id, model);

        add(new Button("submit"));
    }

    @Override
    public void process(IFormSubmitter submittingComponent) {
        try {
            Configuration cfg = getModelObject();
            String pid = cfg.getPid();
            cfg.delete();

            getSession().info("Configuration " + pid + " was removed");
            getRequestCycle().setResponsePage(ConfigurationsPage.class);
        } catch (IOException e) {
            throw new ConfigurationNotFoundException(getModelObject().getPid(), e);
        }
    }

}