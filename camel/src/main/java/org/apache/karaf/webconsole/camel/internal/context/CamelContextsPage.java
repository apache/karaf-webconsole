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

import java.util.Arrays;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.CamelPage;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Camel contexts list page.
 */
@PaxWicketMountPoint(mountPoint = "/camel/contexts")
public class CamelContextsPage extends CamelPage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "contexts")
    private List<CamelContext> contexts;

    @PaxWicketBean(name = "tracer")
    private TraceContainer container;

    @SuppressWarnings("unchecked")
    public CamelContextsPage() {
        @SuppressWarnings("serial")
        IColumn<CamelContext>[] columns = new IColumn[] {
            new OrdinalColumn<CamelContext>(),
            new PropertyColumnExt<CamelContext>("Name", "name"),
            new PropertyColumnExt<CamelContext>("Version", "version"),
            new PropertyColumnExt<CamelContext>("Status", "status"),
            new PropertyColumnExt<CamelContext>("Uptime", "uptime"),
            new AbstractColumn<CamelContext>(Model.of("Message preview")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new Label(componentId, "" + container.isTracePossible(rowModel.getObject())));
                }
            },
            new AbstractColumn<CamelContext>(Model.of("Tracing enabled")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new Label(componentId, "" + container.isTraced(rowModel.getObject())));
                }
            },
            new AbstractColumn<CamelContext>(Model.of("Operations")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new ContextActionsPanel(componentId, rowModel));
                }
            }
        };

        add(new DefaultDataTable<CamelContext>("contexts", Arrays.asList(columns), new CamelContextsDataProvider(contexts), 20));
    }

}
