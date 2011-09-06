package org.apache.karaf.webconsole.camel.internal.tracking;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Tracer {

    List<Map<String, Serializable>> getInfo();

}
