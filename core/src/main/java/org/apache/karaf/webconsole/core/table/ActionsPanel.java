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
package org.apache.karaf.webconsole.core.table;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

/**
 * Actions panel used to render actions in data table.
 */
@SuppressWarnings("rawtypes")
public class ActionsPanel<T> extends Panel {

    public ActionsPanel(String componentId, IModel<T> model) {
        super(componentId, model);

        add(CSSPackageResource.getHeaderContribution(ActionsPanel.class, "actions.css"));

        final List<Link> links = getLinks(model.getObject(), "action");
        add(new ListView<Link>("actions", new ListModel<Link>(links)) {
            @Override
            protected void populateItem(ListItem<Link> item) {
                item.addOrReplace(item.getModelObject());

                if (item.getIndex() == 0) {
                    item.add(new SimpleAttributeModifier("class", "first"));
                } else if (item.getIndex() -1 == links.size()) {
                    item.add(new SimpleAttributeModifier("class", "last"));
                } else {
                    item.add(new SimpleAttributeModifier("class", "node"));
                }
            }
        });
    }

    protected List<Link> getLinks(T object, String id) {
        return Collections.emptyList();
    }

    
}
