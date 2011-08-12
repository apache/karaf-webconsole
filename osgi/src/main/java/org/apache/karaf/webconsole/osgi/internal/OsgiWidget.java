package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.webconsole.core.DashboardWidget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.osgi.framework.BundleContext;

public class OsgiWidget extends Panel implements DashboardWidget {

    public OsgiWidget(BundleContext context) {
        super("none");

        add(new Label("bundleCount", "" + context.getBundles().length));

        add(new BookmarkablePageLink<HomePage>("osgiLink", HomePage.class).add(new Label("osgiLinkLabel", "Manage")));
    }

    public Panel getWidgetPanel() {
        return this;
    }

}
