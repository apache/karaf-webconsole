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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.AdvancedDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Page representing CXF buses list.
 */
public class CxfServicesPage extends SinglePage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "busList")
    private List<Bus> buses;

    @SuppressWarnings("serial")
    public CxfServicesPage() {
        @SuppressWarnings("unchecked")
        IColumn<Bus, String>[] columns = new IColumn[] {
            new OrdinalColumn<Bus>(),
            new PropertyColumnExt<Bus>("Bus id", "id"),
            new AbstractColumn<Bus, String>(Model.of("Actions")) {
                public void populateItem(Item<ICellPopulator<Bus>> cellItem, String componentId, IModel<Bus> rowModel) {
                    cellItem.add(new CxfBusesActionPanel(componentId, rowModel));
                }
            }
        };

        AdvancedDataProvider<Bus> provider = new BaseDataProvider<Bus>() {
            public Iterator<? extends Bus> iterator(long first, long count) {
                return new ArrayList<Bus>(buses).subList((int) first, (int) first + (int) count).iterator();
            }

            public long size() {
                return buses.size();
            }

            public IModel<Bus> model(Bus object) {
                return new BusModel(buses, object);
            }
        };

        add(new BaseDataTable<Bus>("buses", Arrays.asList(columns), provider, 20));
    }

}
