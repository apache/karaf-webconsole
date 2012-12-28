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
import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.AdvancedDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.servicemix.nmr.api.Exchange;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Page which shows list of last exchanges. 
 */
public class TrackNmrPage extends ServiceMixPage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "tracker")
    private ITrackingExchangeListener listener;

    @SuppressWarnings({"unchecked", "serial", "rawtypes"})
    public TrackNmrPage() {
        IColumn<Exchange, String>[] columns = new IColumn[] {
            new PropertyColumnExt<Exchange>("Id", "id"),
            new PropertyColumnExt<Exchange>("Role", "role"),
            new PropertyColumnExt<Exchange>("Status", "status"),
            new PropertyColumnExt<Exchange>("Pattern", "pattern"),
            new PropertyColumnExt<Exchange>("Operation", "operation"),
            new AbstractColumn<Exchange, String>(Model.of("Operations")) {
                public void populateItem(Item<ICellPopulator<Exchange>> cellItem, String componentId, IModel<Exchange> rowModel) {
                    cellItem.add(new ExchangeActionsPanel(componentId, rowModel));
                }
            }
        };

        AdvancedDataProvider<Exchange> dataProvider = new BaseDataProvider<Exchange>() {

            public Iterator<? extends Exchange> iterator(long first, long count) {
                return new ArrayList(listener.getExchanges()).subList((int) first, (int) first + (int) count).iterator();
            }

            public long size() {
                return listener.getExchanges().size();
            }

            public IModel<Exchange> model(Exchange object) {
                return new ExchangeModel(listener, object);
            }

        };

        add(new BaseDataTable<Exchange>("exchanges", Arrays.asList(columns), dataProvider, 20));

    }

}
