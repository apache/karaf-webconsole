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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.spi.InterceptStrategy;

public class TraceInterceptStrategy implements InterceptStrategy, Tracer {

    private LinkedBlockingDeque<Map<String, Serializable>> data = new LinkedBlockingDeque<Map<String,Serializable>>(20);
    private TraceContainer container;

    public TraceInterceptStrategy(TraceContainer container) {
        this.container = container;
    }

    public Processor wrapProcessorInInterceptors(CamelContext context,
        ProcessorDefinition<?> definition,
        Processor target,
        Processor nextTarget) throws Exception {

        Map<String,Serializable> properties = Collections.synchronizedMap(new HashMap<String, Serializable>());
        properties.put("context", context.getName());
        properties.put("version", context.getVersion());

        data.add(properties);

        TraceProcessor tracer = new TraceProcessor(properties, target);
        container.register(context, this);

        return tracer;
    }

    public List<Map<String, Serializable>> getInfo() {
        return new ArrayList<Map<String, Serializable>>(data);
    }

}
