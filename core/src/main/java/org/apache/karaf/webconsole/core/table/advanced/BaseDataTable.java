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
package org.apache.karaf.webconsole.core.table.advanced;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

/**
 * Base class for data tables.
 *
 * @param <T> Type of model.
 */
public class BaseDataTable<T> extends DefaultDataTable<T, String> {

    private static final long serialVersionUID = 1L;

    public BaseDataTable(String id, List<IColumn<T, String>> columns, AdvancedDataProvider<T> dataProvider,
        int rowsPerPage) {
        super(id, columns, dataProvider, rowsPerPage);
    }

}
