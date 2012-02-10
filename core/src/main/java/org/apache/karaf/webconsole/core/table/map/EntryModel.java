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

import java.util.Map.Entry;

import org.apache.wicket.model.IModel;

/**
 * Entry model for table row.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class EntryModel<K, V> implements IModel<Entry<K, V>> {

    private static final long serialVersionUID = 1L;

    private Entry<K, V> object;

    public EntryModel(Entry<K, V> object) {
        this.object = object;
    }

    public void detach() {
        this.object = null;
    }

    public Entry<K, V> getObject() {
        return object;
    }

    public void setObject(Entry<K, V> object) {
        this.object = object;
    }

}
