package org.apache.karaf.webconsole.osgi.internal.widget;

import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.osgi.framework.BundleContext;

public class OsgiWidgetProvider implements WidgetProvider {

    private final BundleContext context;

    public OsgiWidgetProvider(BundleContext context) {
        this.context = context;
        
    }

    public Panel getWidgetPanel(String id) {
        return new OsgiWidgetPanel(id, context);
    }

}
