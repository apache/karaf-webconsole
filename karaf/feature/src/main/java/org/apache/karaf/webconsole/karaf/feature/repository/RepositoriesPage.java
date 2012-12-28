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
package org.apache.karaf.webconsole.karaf.feature.repository;

import java.util.Arrays;

import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.karaf.webconsole.karaf.feature.KarafFeaturesPage;
import org.apache.karaf.webconsole.karaf.feature.RepositoriesProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Page with list of repositories.
 */
@PaxWicketMountPoint(mountPoint = "/karaf/repositories")
public class RepositoriesPage extends KarafFeaturesPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({"rawtypes", "serial", "unchecked"})
    public RepositoriesPage() {
        IColumn<Repository, String>[] columns = new IColumn[] {
            new OrdinalColumn<Repository>(),
            new PropertyColumn<Repository, String>(Model.of("name"), "name", "name"),
            new PropertyColumn<Repository, String>(Model.of("URI"), "URI", "URI"),
            new PropertyColumn<Repository, String>(Model.of("valid"), "valid", "valid"),
            new AbstractColumn<Repository, String>(Model.of("Operations")) {
                public void populateItem(Item<ICellPopulator<Repository>> cellItem, String componentId, IModel<Repository> rowModel) {
                    cellItem.add(new RepositoriesActionPanel(componentId, rowModel));
                }
            }
        };

        add(new AjaxFallbackLink("addRepository") {
            @Override
            public void onClick(AjaxRequestTarget target) {
//                if (target != null) {
//                    target.addComponent(new AddRepositoryPanel(id, model));
//                } else {
                    RequestCycle.get().setResponsePage(AddRepositoryPage.class);
//                }
            }
        });

        add(new BaseDataTable<Repository>("repositories", Arrays.asList(columns), new RepositoriesProvider(featuresService), 20));
    }

}
