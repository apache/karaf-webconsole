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
package org.apache.karaf.webconsole.osgi.blueprint.bundle;

import static org.apache.wicket.model.Model.of;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * Column representing items blueprint state.
 */
class BlueprintColumn extends AbstractColumn<Bundle> {

    private static final long serialVersionUID = 1L;

    public BlueprintColumn() {
        super(of("Blueprint"));
    }

    public void populateItem(Item<ICellPopulator<Bundle>> cellItem, String componentId, IModel<Bundle> rowModel) {
        cellItem.add(new BlueprintPanel(componentId, rowModel));
    }
}