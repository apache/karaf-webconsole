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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

/**
 * Implementation of console tab which allows external providers to put own
 * items to it.
 */
public class ExtendableConsoleTabProvider implements ConsoleTabProvider {

    private Collection<NavigationProvider> extensions;
    private ConsoleTabProvider base;

    public ExtendableConsoleTabProvider(ConsoleTabProvider base) {
        this.base = base;
    }

    public Link<Page> getModuleLink(String componentId, String labelId) {
        return base.getModuleLink(componentId, labelId);
    }

    public List<Link<Page>> getItems(String componentId, String labelId) {
        // create new list because instance returned from base provider may be read only..
        List<Link<Page>> items = new ArrayList<Link<Page>>(base.getItems(componentId, labelId));

        for (NavigationProvider provider : extensions) {
            items.addAll(provider.getItems(componentId, labelId));
        }

        return items;
    }

    public void setExtensions(Collection<NavigationProvider> extensions) {
        this.extensions = extensions;
    }
}
