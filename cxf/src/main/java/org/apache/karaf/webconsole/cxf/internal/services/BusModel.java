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

import java.util.List;

import org.apache.cxf.Bus;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Model of CXF bus.
 */
public class BusModel extends LoadableDetachableModel<Bus> {

    private static final long serialVersionUID = 1L;
    private List<Bus> buses;
    private String id;

    public BusModel(List<Bus> buses, Bus object) {
        super(object);
        this.buses = buses;
        this.id = object.getId();
    }

    public BusModel(List<Bus> buses, String id) {
        this.buses = buses;
        this.id = id;
    }

    @Override
    protected Bus load() {
        for (Bus bus : buses) {
            if (id.equals(bus.getId())) {
                return bus;
            }
        }

        throw new IllegalArgumentException("Bus " + id + " not found");
    }

}
