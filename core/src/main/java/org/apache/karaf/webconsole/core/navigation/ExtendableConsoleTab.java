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
package org.apache.karaf.webconsole.core.navigation;

import java.util.Collection;
import java.util.Map;

import org.apache.wicket.Page;

/**
 * Implementation of console tab which allows external providers to put own
 * items to it.
 */
public class ExtendableConsoleTab implements ConsoleTab {

    private Collection<NavigationProvider> extensions;
    private ConsoleTab base;

    public ExtendableConsoleTab(ConsoleTab base) {
        this.base = base;
    }

    public String getLabel() {
        return base.getLabel();
    }

    public Class<? extends Page> getModuleHomePage() {
        return base.getModuleHomePage();
    }

    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> items = base.getItems();

        for (NavigationProvider provider : extensions) {
            items.putAll(provider.getItems());
        }

        return items;
    }

    public void setExtensions(Collection<NavigationProvider> extensions) {
        this.extensions = extensions;
    }
}
