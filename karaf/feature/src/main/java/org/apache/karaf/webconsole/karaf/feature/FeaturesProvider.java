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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class FeaturesProvider extends SortableDataProvider<Feature> {

    private final FeaturesService service;

    public FeaturesProvider(FeaturesService service) {
        this.service = service;

        setSort("name", true);
    }

    public Iterator<Feature> iterator(int first, int count) {
        List<Feature> data = new ArrayList<Feature>();
        try {
            data = Arrays.asList(service.listFeatures());
        } catch (Exception e) {
            return data.iterator();
        }

        Collections.sort(data, new Comparator<Feature>() {

            public int compare(Feature o1, Feature o2) {
                int dir = getSort().isAscending() ? 1 : -1;

                if ("name".equals(getSort().getProperty())) {
                    return dir * (o1.getName().compareTo(o2.getName()));
//                } else if ("repository".equals(getSort().getProperty())) {
//                    return dir * (o1.getRepository().compareTo(o2.getRepository()));
//                } else if ("state".equals(getSort().getProperty())) {
//                    return dir * (o1.getState().compareTo(o2.getState()));
//                } else {
//                    return dir * (o1.getName().compareTo(o2.getName()));
                }
                return 0;
            }
        });
        return data.subList(first, Math.min(first + count, data.size()))
            .iterator();
    }

    public int size() {
        try {
            return service.listFeatures().length;
        } catch (Exception e) {
            return 0;
        }
    }

    public IModel<Feature> model(Feature object) {
        return new FeatureModel(service, object);
    }

}

