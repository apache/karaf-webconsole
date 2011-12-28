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

import org.apache.karaf.webconsole.osgi.config.model.ConfigurationModel;
import org.apache.karaf.webconsole.osgi.config.view.ConfigurationRemoveForm;
import org.apache.karaf.webconsole.osgi.framework.OsgiPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Configuration remove page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/configuration/remove")
public class ConfigurationRemovePage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;
    private String pid;

    public ConfigurationRemovePage(PageParameters params) {
        pid = params.getString("pid");

        add(new Label("pid", pid));
        add(new ConfigurationRemoveForm("remove", new ConfigurationModel(pid, configurationAdmin)));
    }

}
