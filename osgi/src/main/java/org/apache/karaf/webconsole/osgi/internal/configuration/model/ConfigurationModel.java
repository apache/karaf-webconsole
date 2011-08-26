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
package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import java.io.IOException;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationModel extends LoadableDetachableModel<Configuration> {

    private String pid;
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationModel(String pid, ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
        this.pid = pid;
    }

    public ConfigurationModel(Configuration configuration, ConfigurationAdmin configurationAdmin) {
        super(configuration);
        this.configurationAdmin = configurationAdmin;
        this.pid = configuration.getPid();
    }

    @Override
    protected Configuration load() {
        try {
            return configurationAdmin.getConfiguration(pid);
        } catch (IOException e) {
            throw new ConfigurationNotFoundException(pid, e);
        }
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        setObject(null);
    }
}
