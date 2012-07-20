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

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Datatable which shows list of configurations from OSGi {@link ConfigurationAdmin}.
 */
public class ConfigurationsDataTable extends DefaultDataTable<Configuration> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({"rawtypes", "serial", "unchecked"})
    private static List COLUMNS = Arrays.asList(
        new PropertyColumn<Configuration>(Model.of("pid"), "pid"),
        new AbstractColumn<Configuration>(Model.of("operations")) {
            public void populateItem(Item<ICellPopulator<Configuration>> cellItem, String componentId, IModel<Configuration> model) {
                cellItem.add(new ConfigurationsActionPanel(componentId, model));
            }
        }
    );

    @SuppressWarnings("unchecked")
    public ConfigurationsDataTable(String id, ISortableDataProvider<Configuration> dataProvider, int rowsPerPage) {
        super(id, COLUMNS, dataProvider, rowsPerPage);
    }

    @Override
    protected Item<Configuration> newRowItem(String id, int index,
            IModel<Configuration> model) {
        Configuration cfg = model.getObject();

        if (cfg.getFactoryPid() != null) {
            return new FactoryPidItem(id, index, model);
        }

        return super.newRowItem(id, index, model);
    }
}