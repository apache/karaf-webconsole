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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * OSGi service registry data provider. Scans registry for services matching
 * given criteria.
 * <strong>The service list might be outdated before model is used!</strong>
 */
public class ServiceDataProvider extends BaseDataProvider<ServiceReference> {

    private static final long serialVersionUID = 1L;

    /**
     * List of services.
     */
    private final transient List<ServiceReference> services;

    /**
     * Base constructor.
     * 
     * @param context Bundle context.
     * @param clazz Class name.
     * @param filter Filter to apply.
     */
    public ServiceDataProvider(ServiceReference ... services) {
        if (services != null) {
            this.services = Arrays.asList(services);
        } else {
            this.services = new ArrayList<ServiceReference>();
        }
    }

    public ServiceDataProvider(BundleContext context, String clazz, String filter) {
        this(retrieveServices(context, clazz, filter));
    }

    private static ServiceReference[] retrieveServices(BundleContext context, String clazz, String filter) {
        try {
            ServiceReference[] references = context.getServiceReferences(clazz, filter);
            return references != null ? references : new ServiceReference[0];
        } catch (InvalidSyntaxException e) {
            throw new RuntimeException("Unable to list services", e);
        }
    }

    public ServiceDataProvider(BundleContext context, String clazz) {
        this(context, clazz, "(objectClass=*)");
    }

    public ServiceDataProvider(BundleContext context, Class<?> clazz, String filter) {
        this(context, clazz.getName(), filter);
    }

    public ServiceDataProvider(BundleContext context, Class<?> clazz) {
        this(context, clazz.getName());
    }

    // provider methods
    public Iterator<? extends ServiceReference> iterator(long first, long count) {
        return services.subList((int) first, (int) count).iterator();
    }

    public IModel<ServiceReference> model(ServiceReference object) {
        return new ServiceReferenceModel(object);
    }

    public long size() {
        return services.size();
    }

}
