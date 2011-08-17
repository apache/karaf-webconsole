package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.DashboardWidget;
import org.apache.karaf.webconsole.osgi.internal.widget.FeaturesWidgetPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class KarafFeaturesWidget implements DashboardWidget {

    private final FeaturesService service;

    public KarafFeaturesWidget(FeaturesService service) {
        this.service = service;
    }

    public Panel getWidgetPanel(String id) {
        return new FeaturesWidgetPanel(id, service);
    }

}
