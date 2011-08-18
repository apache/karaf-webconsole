package org.apache.karaf.webconsole.karaf.internal.widget;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.markup.html.panel.Panel;

public class KarafFeaturesWidgetProvider implements WidgetProvider {

    private final FeaturesService service;

    public KarafFeaturesWidgetProvider(FeaturesService service) {
        this.service = service;
    }

    public Panel getWidgetPanel(String id) {
        return new FeaturesWidgetPanel(id, service);
    }

}
