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
package org.apache.karaf.webconsole.core.navigation.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.core.navigation.NavigationProvider;
import org.apache.wicket.Page;
import org.apache.wicket.model.ResourceModel;

public class LocalizableNavigationProvider implements NavigationProvider {

    private final NavigationProvider provider;


    public LocalizableNavigationProvider(NavigationProvider provider) {
        this.provider = provider;
    }


    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> items = new HashMap<String, Class<? extends Page>>();

        for (Entry<String, Class<? extends Page>> entry : provider.getItems().entrySet()) {
            items.put(new ResourceModel(entry.getKey(), entry.getKey()).getObject(), entry.getValue());
        }

        return items;
    }

}
