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
