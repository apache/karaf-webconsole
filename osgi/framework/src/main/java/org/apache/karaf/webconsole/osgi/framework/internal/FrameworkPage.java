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
package org.apache.karaf.webconsole.osgi.framework.internal;

import org.apache.karaf.webconsole.osgi.framework.OsgiPage;
import org.apache.karaf.webconsole.osgi.framework.SystemBundleModel;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;

/**
 * Initial page for OSGi module.
 */
@PaxWicketMountPoint(mountPoint = "/osgi")
@AuthorizeInstantiation({"admin", "osgi-user", "osgi-developer"})
public class FrameworkPage extends OsgiPage {

    public FrameworkPage() {
        setDefaultModel(new CompoundPropertyModel<Bundle>(new SystemBundleModel(context)));

        add(new Label("symbolicName"));
        add(new Label("version"));
    }

}
