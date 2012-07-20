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
package org.apache.karaf.webconsole.karaf.admin.list;

import static org.apache.wicket.model.Model.of;

import java.util.Arrays;

import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.karaf.admin.AdminPage;
import org.apache.karaf.webconsole.karaf.admin.model.InstanceDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Page which shows all karaf instances.
 */
@PaxWicketMountPoint(mountPoint = "/karaf/instance")
public class InstancePage extends AdminPage {

    @SuppressWarnings("unchecked")
    public InstancePage() {
        IColumn<Instance>[] columns = new IColumn[] {
            new OrdinalColumn<Instance>(),
            new PropertyColumnExt<Instance>("PID", "pid"),
            new PropertyColumnExt<Instance>("Name", "name"),
            new PropertyColumnExt<Instance>("Root", "root"),
            new PropertyColumnExt<Instance>("State", "state"),
            new PropertyColumnExt<Instance>("Location", "location"),
            new AbstractColumn<Instance>(of("Actions")) {
                private static final long serialVersionUID = 1L;

                public void populateItem(Item<ICellPopulator<Instance>> cellItem, String componentId, IModel<Instance> rowModel) {
                    cellItem.add(new InstanceActionsPanel(componentId, rowModel));
                }
            }
        };

        add(new DefaultDataTable<Instance>("instances", Arrays.asList(columns), new InstanceDataProvider(admin), 20));
    }

}
