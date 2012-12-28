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
package org.apache.karaf.webconsole.core.table.map;

import java.util.Arrays;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.AdvancedDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * Generic map table.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class MapDataTable<K, V> extends BaseDataTable<Entry<K, V>> {

    private static final long serialVersionUID = 1L;

    public MapDataTable(String id, IColumn<Entry<K, V>, String>[] columns, AdvancedDataProvider<Entry<K, V>> dataProvider, int rowsPerPage) {
        super(id, Arrays.asList(columns), dataProvider, rowsPerPage);
    }

    public MapDataTable(String id, AdvancedDataProvider<Entry<K, V>> dataProvider, int rowsPerPage) {
        this(id, getDefaultColumns(dataProvider), dataProvider, rowsPerPage);
    }

    @SuppressWarnings("unchecked")
    static <K,V> IColumn<Entry<K, V>, String>[] getDefaultColumns(IDataProvider<Entry<K, V>> provider) {
        IColumn<Entry<K, V>, String>[] columns = new IColumn[] {
            new OrdinalColumn<Entry<K, V>>(),
            new PropertyColumnExt<Entry<K, V>>("Key", "key"),
            new PropertyColumnExt<Entry<K, V>>("Value", "value")
        };

        return columns;
    }
}
