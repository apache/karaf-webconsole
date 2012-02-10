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
 * distributed under the License is .istributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.osgi.core.shared;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * Bundle model, allows to use a bundle properties wrapped in CompoundPropertyModel.
 */
public class BundleModel extends LoadableDetachableModel<Bundle> {

    private static final long serialVersionUID = 1L;

    private BundleContext context;
    private long bundleId;

    public BundleModel(BundleContext context, Bundle bundle) {
        super(bundle);
        this.context = context;
        bundleId = bundle.getBundleId();
    }
    
    public BundleModel(Bundle bundle) {
        this(bundle.getBundleContext(), bundle);
    }

    @Override
    protected Bundle load() {
        return context.getBundle(bundleId);
    }

}
