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
package org.apache.karaf.webconsole.servicemix.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.servicemix.nmr.api.Exchange;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * Actions panel for Exchange.
 */
public class ExchangeActionsPanel extends ActionsPanel<Exchange> {

    private static final long serialVersionUID = 1L;

    public ExchangeActionsPanel(String componentId, IModel<Exchange> model) {
        super(componentId, model);
    }

    @Override
    @SuppressWarnings({"rawtypes", "serial"})
    protected List<Link> getLinks(final Exchange object, String linkId, String labelId) {
        List<Link> links = new ArrayList<Link>();

        Link link = new Link(linkId) {
            @Override
            public void onClick() {
                RequestCycle.get().setResponsePage(new DetailsPage(object));
            }
        };
        link.add(new Label(labelId, "Details"));

        links.add(link);

        return links;
    }

}
