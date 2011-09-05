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
package org.apache.karaf.webconsole.blueprint.internal.details;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.BundleContext;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

/**
 * Page with blueprint container details.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/blueprint/details")
public class DetailsPage extends SinglePage {

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;

    public DetailsPage(PageParameters params) {
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "jquery-1.4.2.min.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "Curry-1.0.1.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "seedrandom.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "raphael-min.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "dracula_graph.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "dracula_graffle.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "dracula_algorithms.js"));
        add(JavascriptPackageResource.getHeaderContribution(DetailsPage.class, "details.js"));

        IModel<List<ComponentMetadata>> model = new MetadataModel(context, params.getInt("bundleId"));

        add(new ListView<ComponentMetadata>("components", model) {
            @Override
            @SuppressWarnings("unchecked")
            protected void populateItem(ListItem<ComponentMetadata> item) {
                ComponentMetadata metadata = item.getModelObject();
                item.add(new Label("componentId", metadata.getId()));
                item.add(new Label("type", Arrays.toString(metadata.getClass().getInterfaces())));
                //item.add(new Label("dependencies", "" + metadata.getDependsOn()));
                item.add(new ListView<String>("dependencies", metadata.getDependsOn()) {
                    @Override
                    protected void populateItem(ListItem<String> item) {
                        item.add(new Label("dependency", item.getModelObject()).setRenderBodyOnly(true));
                    }
                });
            }
        });
    }

}
