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
package org.apache.karaf.webconsole.osgi.core.service;

import java.util.Arrays;

import org.apache.karaf.webconsole.osgi.core.service.column.ObjectClassColumn;
import org.apache.karaf.webconsole.osgi.core.service.column.ServiceConsumerColumn;
import org.apache.karaf.webconsole.osgi.core.service.column.ServicePropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.Model;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

public class ExportServiceTable extends DefaultDataTable<ServiceReference> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    private static IColumn<ServiceReference>[] columns = new IColumn[] {
        new ServicePropertyColumn("Service Id", Constants.SERVICE_ID),
        new ObjectClassColumn(Model.of("Object classes")),
        new ServiceConsumerColumn("Consumers"),
    };

    public ExportServiceTable(String id, Bundle bundle) {
        super(id, Arrays.asList(columns), new ExportServiceDataProvider(bundle), Integer.MAX_VALUE);
    }

}
