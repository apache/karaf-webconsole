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
package org.apache.karaf.webconsole.camel.internal.tracking;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;

public class DefaultTracerContainer implements TraceContainer {

    private Map<CamelContext, Tracer> tracers = Collections.synchronizedMap(new HashMap<CamelContext, Tracer>());

    public boolean isTraced(CamelContext context) {
        return isTracePossible(context) && context.isTracing();
    }

    public boolean isTracePossible(CamelContext context) {
        return getTracer(context) != null;
    }

    public Tracer getTracer(CamelContext context) {
        return tracers.get(context);
    }

    public void register(CamelContext context, Tracer tracer) {
        tracers.put(context, tracer);
    }

}
