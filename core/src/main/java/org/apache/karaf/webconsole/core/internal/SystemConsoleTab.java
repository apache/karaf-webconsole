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
package org.apache.karaf.webconsole.core.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.Page;

public class SystemConsoleTab implements ConsoleTab, Serializable {

    public String getLabel() {
        return "dashboard";
    }

    public Class<? extends Page> getModuleHomePage() {
        return DashboardPage.class;
    }

    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> map = new HashMap<String, Class<? extends Page>>();
        map.put("dashboard", DashboardPage.class);
        return map;
    }

}
