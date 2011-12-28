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
package org.apache.karaf.webconsole.osgi.blueprint.view;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.ServiceReference;

public abstract class ServiceReferencePropertyColumn extends AbstractColumn<ServiceReference> {

    public ServiceReferencePropertyColumn(String displayModel, String sortProperty) {
        super(Model.of(displayModel), sortProperty);
    }

    public ServiceReferencePropertyColumn(String displayModel) {
        super(Model.of(displayModel));
    }

    public final void populateItem(Item<ICellPopulator<ServiceReference>> cellItem, String componentId, IModel<ServiceReference> rowModel) {
        cellItem.add(createComponent(componentId, rowModel.getObject().getProperty(getSortProperty())));
    }

    protected abstract Component createComponent(String componentId, Object property);
}
