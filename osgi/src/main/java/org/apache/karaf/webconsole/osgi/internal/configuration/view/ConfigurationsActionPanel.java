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
package org.apache.karaf.webconsole.osgi.internal.configuration.view;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationEditPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationRemovePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

@SuppressWarnings("rawtypes")
class ConfigurationsActionPanel extends ActionsPanel<Configuration> {

    public ConfigurationsActionPanel(String componentId, IModel<Configuration> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Configuration object, String id) {
        PageParameters params = new PageParameters();
        params.put("pid", object.getPid());

        Link removeLink = new BookmarkablePageLink<ConfigurationEditPage>(id, ConfigurationRemovePage.class, params);
        removeLink.add(new SimpleAttributeModifier("class", "remove"));
        removeLink.add(new Label("label", "remove"));

        Link editLink = new BookmarkablePageLink<ConfigurationEditPage>(id, ConfigurationEditPage.class, params);
        removeLink.add(new SimpleAttributeModifier("class", "edit"));
        editLink.add(new Label("label", "edit"));

        return Arrays.asList(editLink, removeLink);
    }
}