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
package org.apache.karaf.webconsole.camel.internal.widget;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.context.CamelContextsPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Widget with list of camel contexts.
 */
public class CamelWidget extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "contexts")
    private List<CamelContext> contexts;

    @SuppressWarnings("serial")
    public CamelWidget(String id) {
        super(id);

        add(new Label("count", "" + contexts.size()));

        add(new ListView<CamelContext>("contexts", contexts) {
            @Override
            protected void populateItem(ListItem<CamelContext> item) {
                CamelContext model = item.getModelObject();
                item.add(new Label("name", model.getName()));
                item.add(new Label("uptime", model.getUptime()));
                item.add(new Label("routeCount", ""+ model.getRouteDefinitions().size()));
            }
        });

        add(new BookmarkablePageLink<CamelContextsPage>("management", CamelContextsPage.class));
    }

}
