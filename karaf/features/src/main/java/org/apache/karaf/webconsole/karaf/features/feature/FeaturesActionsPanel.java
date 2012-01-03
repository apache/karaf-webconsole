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
package org.apache.karaf.webconsole.karaf.features.feature;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class FeaturesActionsPanel extends ActionsPanel<Feature> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public FeaturesActionsPanel(String componentId, IModel<Feature> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Feature object, String linkId, String labelId) {
        Link link = new Link(linkId) {

            @Override
            public void onClick() {

                try {
                    Feature object = (Feature) FeaturesActionsPanel.this.getDefaultModelObject();
                    if (isInstalled(object)) {
                        featuresService.uninstallFeature(object.getName());
                    } else {
                        featuresService.installFeature(object.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        link.add(new SimpleAttributeModifier("class", isInstalled(object) ? "installed" : "uninstalled"));
        link.add(new Label(labelId));

        // add image to the link
        return Arrays.asList(link);
    }

    protected boolean isInstalled(Feature feature) {
        return featuresService.isInstalled(feature);
    }

    /*

             SubmitLink removeLink = new SubmitLink("install", form) {
                 @Override
                 public void onSubmit()
                 {
                     ExtendedFeature extendedFeature = (ExtendedFeature)ActionPanel.this.getDefaultModelObject();
                     info("Install feature" + extendedFeature);
                     // call features service
                 }
             };
             removeLink.setDefaultFormProcessing(false);
             add(removeLink);*/


}
