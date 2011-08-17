package org.apache.karaf.webconsole.core;

import org.apache.wicket.markup.html.panel.Panel;

public interface DashboardWidget {

    Panel getWidgetPanel(String id);

}
