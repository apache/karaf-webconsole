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
package org.apache.karaf.webconsole.osgi.config;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.webconsole.osgi.config.model.ConfigurationModel;
import org.apache.karaf.webconsole.osgi.config.model.ConfigurationNotFoundException;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationProvider extends SortableDataProvider<Configuration> {

    private Configuration[] configurations = new Configuration[0];
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationProvider(ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
        try {
            this.configurations = configurationAdmin.listConfigurations(null);
        } catch (Exception e) {
            throw new ConfigurationNotFoundException(null, e);
        }
    }

    public Iterator<? extends Configuration> iterator(int from, int count) {
        return Arrays.asList(Arrays.copyOfRange(configurations, from, count)).iterator();
    }

    public IModel<Configuration> model(Configuration object) {
        return new ConfigurationModel(object, configurationAdmin);
    }

    public int size() {
        return configurations.length;
    }

    @Override
    public void detach() {
        super.detach();
        configurations = null;
    }
}
