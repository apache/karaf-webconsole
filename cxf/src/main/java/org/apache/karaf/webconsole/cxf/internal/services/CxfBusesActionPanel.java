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
package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CxfBusesActionPanel extends ActionsPanel<Bus> {

    public CxfBusesActionPanel(String componentId, IModel<Bus> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Bus object, String linkId, String labelId) {
        List<Link> links = new ArrayList<Link>();

        PageParameters params = new PageParameters();
        params.add("busId", object.getId());

        Link link = new BookmarkablePageLink<DetailsPage>(linkId, DetailsPage.class, params);
        link.add(new Label(labelId, "Details"));
        links.add(link);

        return links;
    }
}
