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
package org.apache.karaf.webconsole.osgi.config.view;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.config.ConfigurationEditPage;
import org.apache.karaf.webconsole.osgi.config.ConfigurationRemovePage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.osgi.service.cm.Configuration;

@SuppressWarnings("rawtypes")
class ConfigurationsActionPanel extends ActionsPanel<Configuration> {

    public ConfigurationsActionPanel(String componentId, IModel<Configuration> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Configuration object, String linkId, String labelId) {
        PageParameters params = new PageParameters();
        params.add("pid", object.getPid());

        Link removeLink = new BookmarkablePageLink<ConfigurationEditPage>(linkId, ConfigurationRemovePage.class, params);
        removeLink.add(new AttributeModifier("class", "remove"));
        removeLink.add(new Label(labelId, "remove"));

        Link editLink = new BookmarkablePageLink<ConfigurationEditPage>(linkId, ConfigurationEditPage.class, params);
        removeLink.add(new AttributeModifier("class", "edit"));
        editLink.add(new Label(labelId, "edit"));

        return Arrays.asList(editLink, removeLink);
    }
}