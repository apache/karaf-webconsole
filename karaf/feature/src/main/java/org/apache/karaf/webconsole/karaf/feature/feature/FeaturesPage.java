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
package org.apache.karaf.webconsole.karaf.feature.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.karaf.webconsole.karaf.feature.FeaturesProvider;
import org.apache.karaf.webconsole.karaf.feature.KarafFeaturesPage;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Features
 */
@PaxWicketMountPoint(mountPoint = "/karaf/features")
public class FeaturesPage extends KarafFeaturesPage {

    private static final long serialVersionUID = 1L;

    private Map<String, String> feature2repo = new HashMap<String, String>();

    @SuppressWarnings({"rawtypes", "serial"})
    public FeaturesPage() throws Exception {
        add(new CssBehavior(FeaturesPage.class, "features.css"));

        for (Repository repository : featuresService.listRepositories()) {
            for (Feature feature : repository.getFeatures()) {
                feature2repo.put(feature.getId(), repository.getName());
            }
        }


        IModel state = Model.of("state");
        IModel version = Model.of("version");
        IModel name = Model.of("name");
        IModel repository = Model.of("repository");
        IModel description = Model.of("description");

        List<IColumn<Feature, String>> columns = new ArrayList<IColumn<Feature, String>>();
        columns.add(new PropertyColumn<Feature, String>(new StringResourceModel("table.version", this, version), "version", "version"));
        columns.add(new PropertyColumn<Feature, String>(new StringResourceModel("table.name", this, name), "name", "name"));
        columns.add(new AbstractColumn<Feature, String>(new StringResourceModel("table.repository", this, repository), "repository") {
            public void populateItem(Item<ICellPopulator<Feature>> cellItem, String componentId, IModel<Feature> rowModel) {
                cellItem.add(new Label(componentId, feature2repo.get(rowModel.getObject().getId())));
            }
        });
        columns.add(new PropertyColumn<Feature, String>(new StringResourceModel("table.description", this, description), "description", "description"));
        columns.add(new AbstractColumn<Feature, String>(new StringResourceModel("table.state", this, state), "state") {
            public void populateItem(Item<ICellPopulator<Feature>> cellItem, String componentId, IModel<Feature> rowModel) {
                cellItem.add(new Label(componentId, featuresService.isInstalled(rowModel.getObject()) ? "Installed" : "Uninstalled"));
            }
        });
        columns.add(new AbstractColumn<Feature, String>(new ResourceModel("table.actions")) {
            public void populateItem(Item<ICellPopulator<Feature>> cellItem, String componentId, IModel<Feature> model) {
               cellItem.add(new FeaturesActionsPanel(componentId, model));
            }
        });

        add(new BaseDataTable<Feature>("features", columns, new FeaturesProvider(featuresService), 20));

    }


}
