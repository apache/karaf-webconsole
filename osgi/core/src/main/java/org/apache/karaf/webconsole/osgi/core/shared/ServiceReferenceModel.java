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

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

/**
 * Model to keep service references.
 */
public class ServiceReferenceModel extends LoadableDetachableModel<ServiceReference> {

    private static final long serialVersionUID = 1L;

    private final Bundle bundle;
    private Long serviceId;

    public ServiceReferenceModel(ServiceReference object) {
        this(object, object.getBundle());
    }

    public ServiceReferenceModel(ServiceReference object, Bundle bundle) {
        this.bundle = bundle;
        serviceId = (Long) object.getProperty(Constants.SERVICE_ID);
    }

    @Override
    protected ServiceReference load() {
        ServiceReference[] services = bundle.getRegisteredServices();

        for (ServiceReference reference : services) {
            if (serviceId.equals(reference.getProperty(Constants.SERVICE_ID))) {
                return reference;
            }
        }

        throw new MissingServiceReferenceException(bundle.getSymbolicName(), serviceId);
    }

}
