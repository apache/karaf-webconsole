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
package org.apache.karaf.webconsole.core.form;

import static org.apache.wicket.model.Model.of;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Generic map editing form.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public abstract class MapEditForm<K, V> extends Form<Map<K, V>> {

    private static final long serialVersionUID = 1L;

    public MapEditForm(String id, CompoundPropertyModel<Map<K, V>> model) {
        super(id, model);

        RepeatingView repeatingView = new RepeatingView("entries");

        for (K key : model.getObject().keySet()) {
            IModel<V> bind = model.bind("[" + key + "]");
            repeatingView.add(populateItem(repeatingView.newChildId(), key, bind));
        }
        add(repeatingView);
    }

    protected Component populateItem(String componentId, K key, IModel<V> value) {
        return new LabelBorder(componentId, createField(key, value));
    }

    private FormComponent<?> createField(K key, IModel<V> value) {
        FormComponent<V> field = new TextField<V>("value", value);
        field.setType(String.class);
        field.setLabel(of(key.toString()));
        return field;
    }

}
