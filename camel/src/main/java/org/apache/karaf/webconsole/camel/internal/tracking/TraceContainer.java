package org.apache.karaf.webconsole.camel.internal.tracking;

import org.apache.camel.CamelContext;

public interface TraceContainer {

    Tracer getTracer(CamelContext context);

    boolean isTraced(CamelContext context);

    boolean isTracePossible(CamelContext context);

    void register(CamelContext context, Tracer tracer);

}
