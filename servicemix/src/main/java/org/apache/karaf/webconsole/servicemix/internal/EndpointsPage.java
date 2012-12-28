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
package org.apache.karaf.webconsole.servicemix.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.AdvancedDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.servicemix.nmr.api.Endpoint;
import org.apache.servicemix.nmr.api.EndpointRegistry;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * ServiceMix page, it shows list of registered endpoints.
 */
public class EndpointsPage extends ServiceMixPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({"unchecked", "serial"})
    public EndpointsPage() {
        IColumn<Map<String, Object>, String>[] columns = new IColumn[] {
            new OrdinalColumn<Map<String, Object>>(),
            new PropertyColumnExt<Map<String, Object>>("Name", Endpoint.NAME),
            new PropertyColumnExt<Map<String, Object>>("Version", Endpoint.VERSION),
            new PropertyColumnExt<Map<String, Object>>("Endpoint Name", Endpoint.ENDPOINT_NAME),
            new PropertyColumnExt<Map<String, Object>>("Interface", Endpoint.INTERFACE_NAME),
            new PropertyColumnExt<Map<String, Object>>("Service name", Endpoint.SERVICE_NAME),
            new PropertyColumnExt<Map<String, Object>>("Sync?", Endpoint.CHANNEL_SYNC_DELIVERY),
            new PropertyColumnExt<Map<String, Object>>("Untargetable?", Endpoint.UNTARGETABLE),
            new PropertyColumnExt<Map<String, Object>>("Wsdl url", Endpoint.WSDL_URL)
        };

        AdvancedDataProvider<Map<String, Object>> provider = new BaseDataProvider<Map<String, Object>>() {

            public Iterator<? extends Map<String, Object>> iterator(long first, long count) {
                List<Map<String, Object>> props = new ArrayList<Map<String,Object>>();

                EndpointRegistry endpointRegistry = nmr.getEndpointRegistry();
                for (Endpoint endpoint : endpointRegistry.getServices()) {
                    props.add((Map<String, Object>) endpointRegistry.getProperties(endpoint));
                }

                return props.subList((int) first, (int) count).iterator();
            }

            public long size() {
                return nmr.getEndpointRegistry().getServices().size();
            }

            public IModel<Map<String, Object>> model(Map<String, Object> object) {
                return Model.ofMap(object);
            }
        };

        add(new BaseDataTable<Map<String, Object>>("endpoints", Arrays.asList(columns), provider, 20));
    }

}
