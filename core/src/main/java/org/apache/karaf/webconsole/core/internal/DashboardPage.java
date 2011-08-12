package org.apache.karaf.webconsole.core.internal;

import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.DashboardWidget;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class DashboardPage extends BasePage {

    @PaxWicketBean(name = "widgets")
    private List<DashboardWidget> widgets;

	public DashboardPage() {

	    for (DashboardWidget widget : widgets) {
	        add(widget.getWidgetPanel());
	    }

	}

}
