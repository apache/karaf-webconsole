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
package org.apache.karaf.webconsole.osgi.core.service.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.karaf.webconsole.osgi.core.shared.ServiceDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

/**
 * Service list page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/service")
public class ServicePage extends OsgiPage {

    public ServicePage() {
        List<IColumn<ServiceReference>> columns = new ArrayList<IColumn<ServiceReference>>();
        columns.add(new PropertyColumnExt<ServiceReference>("Service Id") {
            @Override
            protected IModel<?> createLabelModel(IModel<ServiceReference> rowModel) {
                return Model.of((Long) rowModel.getObject().getProperty(Constants.SERVICE_ID));
            }
        });

        columns.add(new PropertyColumnExt<ServiceReference>("Interfaces") {
            @Override
            protected IModel<?> createLabelModel(IModel<ServiceReference> rowModel) {
                return Model.of(Arrays.toString((String[]) rowModel.getObject().getProperty(Constants.OBJECTCLASS)));
            }
        });
        columns.add(new PropertyColumnExt<ServiceReference>("Exporter", "bundle.symbolicName"));

        add(new DefaultDataTable<ServiceReference>("services", columns, new ServiceDataProvider(context, (String) null), 100));
    }

}
