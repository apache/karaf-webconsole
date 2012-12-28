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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;

/**
 * Generic data provider for map table.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class MapDataProvider<K, V> extends BaseDataProvider<Entry<K, V>> {

    private static final long serialVersionUID = 1L;

    private final Map<K, V> map;

    public MapDataProvider(Map<K, V> map) {
        this.map = map;
    }

    public Iterator<? extends Entry<K, V>> iterator(long first, long count) {
        return new ArrayList<Entry<K, V>>(map.entrySet()).subList((int) first, (int) (first + count)).iterator();
    }

    public long size() {
        return map.size();
    }

    public IModel<Entry<K, V>> model(Entry<K, V> object) {
        return new EntryModel<K, V>(object);
    }

}
