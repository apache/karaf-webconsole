package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.webconsole.core.DashboardWidget;
import org.apache.karaf.webconsole.osgi.internal.widget.OsgiWidgetPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.osgi.framework.BundleContext;

public class OsgiWidget implements DashboardWidget {

    private final BundleContext context;

    public OsgiWidget(BundleContext context) {
        this.context = context;
        
    }

    public Panel getWidgetPanel(String id) {
        return new OsgiWidgetPanel(id, context);
    }

}
