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
package org.apache.karaf.webconsole.osgi.core.service.column;

import java.io.Serializable;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.ServiceReference;

public class ServicePropertyColumn extends PropertyColumnExt<ServiceReference> {

    private static final long serialVersionUID = 1L;

    public ServicePropertyColumn(String label, String property) {
        super(label, property);
    }

    @Override
    protected IModel<?> createLabelModel(IModel<ServiceReference> rowModel) {
        Object property = rowModel.getObject().getProperty(getPropertyExpression());

        if (property instanceof Serializable) {
            return Model.of((Serializable) property);
        } else {
            return Model.of(property.toString());
        }
    }
}
