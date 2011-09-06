package org.apache.karaf.webconsole.camel.internal.widget;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.markup.html.panel.Panel;

public class CamelWidgetProvider implements WidgetProvider {

    private final List<CamelContext> contexts;

    public CamelWidgetProvider(List<CamelContext> contexts) {
        this.contexts = contexts;
    }

    public Panel getWidgetPanel(String id) {
        return new CamelWidget(id, contexts);
    }

}
