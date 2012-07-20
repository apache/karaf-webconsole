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
package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.endpoint.ServerRegistry;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.OperationInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.cxf.internal.trace.TraceInterceptor;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class DetailsPage extends SinglePage {

    @PaxWicketBean(name = "busList")
    private List<Bus> buses;

    private TraceInterceptor traceIn = new TraceInterceptor(Phase.RECEIVE, "in");
    private TraceInterceptor traceOut = new TraceInterceptor(Phase.PRE_STREAM, "out");

    public DetailsPage(PageParameters params) {
        String busId = params.get("busId").toString();

        final BusModel model = new BusModel(buses, busId);

        Bus object = model.getObject();

        add(new Link("traceOn") {
            @Override
            public void onClick() {
                Bus bus = model.getObject();

                Session.get().info("Trace enabled");
                bus.getInInterceptors().add(traceIn);
                bus.getOutInterceptors().add(traceOut);
            }
        });

        add(new Link("traceOff") {
            @Override
            public void onClick() {
                Bus bus = model.getObject();

                Session.get().info("Trace disabled");
                bus.getInInterceptors().remove(traceIn);
                bus.getOutInterceptors().remove(traceOut);
            }
        });

        ServerRegistry extension = object.getExtension(ServerRegistry.class);
        LoadableDetachableModel<List<Server>> serverModel = new LoadableDetachableModel<List<Server>>(extension.getServers()) {
            @Override
            protected List<Server> load() {
                return model.getObject().getExtension(ServerRegistry.class).getServers();
            }
        };

        add(new ListView<Server>("servers", serverModel) {
            @Override
            protected void populateItem(ListItem<Server> item) {
                Server server = item.getModelObject();

                item.add(new Label("class", server.getClass().getName()));

                item.add(new Link("stop") {
                    @Override
                    public void onClick() {
                        //server.stop();
                    }
                });
                item.add(new Link("start") {
                    @Override
                    public void onClick() {
                        //server.start();
                    }
                });

                ServiceInfo service = server.getEndpoint().getEndpointInfo().getService();
                item.add(new ListView<EndpointInfo>("endpoints", new ArrayList<EndpointInfo>(service.getEndpoints())) {
                    @Override
                    protected void populateItem(ListItem<EndpointInfo> item) {
                        EndpointInfo endpointInfo = item.getModelObject();

                        item.add(new Label("namespace", endpointInfo.getName().getNamespaceURI()));
                        item.add(new Label("element", endpointInfo.getName().getLocalPart()));
                        item.add(new Label("transport", endpointInfo.getTransportId()));

                        item.add(new ListView<OperationInfo>("operations", new ArrayList<OperationInfo>(endpointInfo.getInterface().getOperations())) {

                            @Override
                            protected void populateItem(ListItem<OperationInfo> item) {
                                OperationInfo operation = item.getModelObject();

                                item.add(new Label("input", "" + operation.getInput().getName()));
                                item.add(new Label("output", "" + operation.getOutput().getName()));
                                item.add(new Label("faults", "" + operation.getFaults()));
                            }
                            
                        });
                    }
                });

                Endpoint endpoint = server.getEndpoint();
                List<Interceptor> inInterceptors = (List) endpoint.getInInterceptors();
                List<Interceptor> outInterceptors = (List) endpoint.getOutInterceptors();
                List<Interceptor> faultInterceptors = (List) endpoint.getInFaultInterceptors();

                item.add(new InterceptorView("in-interceptors", inInterceptors));
                item.add(new InterceptorView("out-interceptors", outInterceptors));
                item.add(new InterceptorView("fault-interceptors", faultInterceptors));
            }
        });
    }
}
