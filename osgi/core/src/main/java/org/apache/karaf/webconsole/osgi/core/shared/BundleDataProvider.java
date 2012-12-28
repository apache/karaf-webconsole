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
package org.apache.karaf.webconsole.osgi.core.shared;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * Data provider to which lists all installed bundles.
 */
public class BundleDataProvider extends BaseDataProvider<Bundle> {

    private static final long serialVersionUID = 1L;
    private BundleContext context;

    public BundleDataProvider(BundleContext context) {
        this.context = context;
    }

    public Iterator<? extends Bundle> iterator(long first, long count) {
        List<Bundle> bundles = Arrays.asList(context.getBundles());

        return bundles.subList((int) first, (int) first + (int) count).iterator();
    }

    public IModel<Bundle> model(Bundle object) {
        return new BundleModel(context, object);
    }

    public long size() {
        return context.getBundles().length;
    }

}
