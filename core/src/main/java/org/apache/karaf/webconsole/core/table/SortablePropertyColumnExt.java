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
package org.apache.karaf.webconsole.core.table;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

/**
 * Sortable column which wraps given label in serializable model.
 *
 * @param <T> Type of displayed data.
 */
public class SortablePropertyColumnExt<T> extends PropertyColumn<T, String> {

    private static final long serialVersionUID = 1L;

    public SortablePropertyColumnExt(String label, String property) {
        super(Model.of(label), property, property);
    }

    public SortablePropertyColumnExt(String property) {
        this(property, property);
    }

}
