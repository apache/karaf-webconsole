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
package org.apache.karaf.webconsole.camel.internal.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class ContextActionsPanel extends ActionsPanel<CamelContext> {

    @PaxWicketBean(name = "tracer")
    private TraceContainer container;

    public ContextActionsPanel(String componentId, IModel<CamelContext> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(CamelContext object, String linkId, String labelId) {
        List<Link> links = new ArrayList<Link>();

        if (!object.isTracing()) {
            Link link = new Link<CamelContext>(linkId) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    context.setTracing(true);
                    setResponsePage(CamelContextsPage.class);
                    Session.get().info("Tracing enabled for context " + context.getName());
                }
            };
            link.add(new Label(labelId, "Enable tracing"));
            links.add(link);
        } else {
            Link link = new Link<CamelContext>(linkId) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    context.setTracing(false);
                    setResponsePage(CamelContextsPage.class);
                    Session.get().info("Tracing disabled for context " + context.getName());
                }
            };
            link.add(new Label(labelId, "Disable tracing"));
            links.add(link);
        }

        if (container.isTracePossible(object)) {
            Link link = new Link<CamelContext>(linkId) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    setResponsePage(new DumpPage(container, context));
                }
            };
            link.add(new Label(labelId, "View messages"));
            links.add(link);
        }

        return links;
    }

}
