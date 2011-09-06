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
