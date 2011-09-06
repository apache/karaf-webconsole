package org.apache.karaf.webconsole.camel.internal.context;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.wicket.model.LoadableDetachableModel;

public class CamelContextModel extends LoadableDetachableModel<CamelContext> {

    private String name;
    private List<CamelContext> contexts;

    public CamelContextModel(List<CamelContext> contexts, CamelContext object) {
        super(object);

        this.contexts = contexts;
        name = object.getName();
    }

    protected CamelContext load() {
        for (CamelContext context : contexts) {
            if (name.equals(context.getName())) {
                return context;
            }
        }

        throw new IllegalArgumentException("Camel context " + name + " not found");
    }

}
