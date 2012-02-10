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
package org.apache.karaf.webconsole.osgi.log;

import java.util.Arrays;

import org.apache.wicket.extensions.markup.html.form.select.IOptionRenderer;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOptions;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Search configuration form.
 */
public class OptionsForm extends Form<Options> {

    private static final long serialVersionUID = 1L;

    public OptionsForm(String id, CompoundPropertyModel<Options> model) {
        super(id, model);

        IModel<Priority> priority = model.bind("priority");
        Select<Priority> select = new Select<Priority>("priority", priority);
        select.add(new SelectOptions<Priority>("options", Arrays.asList(Priority.values()), new IOptionRenderer<Priority>() {
            public String getDisplayValue(Priority object) {
                return object.name();
            }

            public IModel<Priority> getModel(Priority value) {
                return Model.of(value);
            }
        }));

        add(select);
        add(new TextField<Long>("dateFrom", Long.class));
        add(new TextField<Long>("dateTo", Long.class));
        add(new TextField<String>("messageFragment",String.class));
        add(new TextField<String>("bundleNameFragment",String.class));


        add(new SubmitLink("submit"));
    }

}
