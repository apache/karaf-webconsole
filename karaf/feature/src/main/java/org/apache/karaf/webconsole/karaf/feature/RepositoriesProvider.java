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

import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.karaf.feature.model.RepositoryModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class RepositoriesProvider extends SortableDataProvider<Repository> {

    private final FeaturesService service;

    public RepositoriesProvider(FeaturesService service) {
        this.service = service;

    }

    public Iterator<Repository> iterator(int first, int count) {
        return Arrays.asList(service.listRepositories())
                .subList(first, first + count).iterator();
    }

    public int size() {
        return service.listRepositories().length;
    }

    public IModel<Repository> model(Repository object) {
        return new RepositoryModel(service, object);
    }
}