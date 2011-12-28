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

import org.apache.karaf.webconsole.osgi.config.view.ConfigurationsDataTable;
import org.apache.karaf.webconsole.osgi.framework.OsgiPage;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.ConfigurationAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/configuration")
public class ConfigurationsPage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationsPage() {
        add(CSSPackageResource.getHeaderContribution(ConfigurationsPage.class, "configurations.css"));

        add(new ConfigurationsDataTable("configurations", new ConfigurationProvider(configurationAdmin), 20));

    }

}
