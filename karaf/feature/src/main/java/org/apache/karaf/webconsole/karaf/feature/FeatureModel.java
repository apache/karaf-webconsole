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
package org.apache.karaf.webconsole.karaf.feature;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.wicket.model.LoadableDetachableModel;

public class FeatureModel extends LoadableDetachableModel<Feature> {

    private FeaturesService service;
    private String version;
    private String name;

    public FeatureModel(FeaturesService service, Feature object) {
        this.service = service;
        this.name = object.getName();
        this.version = object.getVersion();
    }

    @Override
    protected Feature load() {
        Feature[] features = new Feature[0];

        try {
            features = service.listFeatures();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Feature feature : features) {
            if (name.equals(feature.getName()) && version.equals(feature.getVersion())) {
                return feature;
            }
        }

        throw new MissingFeatureException(name, version);
    }
}
