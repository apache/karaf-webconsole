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
package org.apache.karaf.webconsole.karaf.core;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.karaf.core.model.WicketKaraf;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Overview page which shows karaf properties.
 */
@PaxWicketMountPoint(mountPoint = "/karaf")
public class KarafOverviewPage extends SinglePage {

    public KarafOverviewPage() {
        setDefaultModel(new CompoundPropertyModel<WicketKaraf>(new WicketKaraf()));

        add(new Label("version"));

        add(new Label("home"));
        add(new Label("base"));
        add(new Label("data"));
        add(new Label("instances"));

        add(new Label("localConsole"));
        add(new Label("remoteShell"));
    }

}
