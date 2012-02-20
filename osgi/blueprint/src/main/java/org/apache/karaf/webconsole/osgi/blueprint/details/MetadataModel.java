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
package org.apache.karaf.webconsole.osgi.blueprint.details;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

class MetadataModel extends LoadableDetachableModel<List<ComponentMetadata>> {

    private static final long serialVersionUID = 1L;

    private int bundleId;
    private BundleContext context;
    private ServiceReference reference;

    public MetadataModel(BundleContext context, int bundleId) {
        this.context = context;
        this.bundleId = bundleId;
    }

    @Override
    protected List<ComponentMetadata> load() {
        Bundle bundle = context.getBundle(bundleId);

        ServiceReference[] references = bundle.getRegisteredServices();
        for (ServiceReference ref : references) {
            String[] classes = (String[]) ref.getProperty("objectClass");
            for (String clazz : classes) {
                if ("org.osgi.service.blueprint.container.BlueprintContainer".equals(clazz)) {
                    reference = ref;
                    List<ComponentMetadata> metas = new ArrayList<ComponentMetadata>();
                    metas.addAll(((BlueprintContainer) context.getService(ref)).getMetadata(ComponentMetadata.class));
                    return metas;
                }
            }
        }
        throw new IllegalArgumentException("Cannot find blueprint container for bundle " + bundleId);
    }

    @Override
    public void detach() {
        if (reference != null) {
            context.ungetService(reference);
        }
    }
}