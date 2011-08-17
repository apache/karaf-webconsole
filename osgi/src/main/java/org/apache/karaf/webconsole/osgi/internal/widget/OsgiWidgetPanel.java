package org.apache.karaf.webconsole.osgi.internal.widget;

import org.apache.karaf.webconsole.osgi.internal.HomePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.osgi.framework.BundleContext;

public class OsgiWidgetPanel extends Panel {

    public OsgiWidgetPanel(String id, BundleContext context) {
        super(id);

        add(new Label("framework", context.getBundle(0).getSymbolicName()));
        add(new Label("version", context.getBundle(0).getVersion().toString()));

        add(new Label("bundleCount", "" + context.getBundles().length));

        add(new BookmarkablePageLink<HomePage>("osgiLink", HomePage.class).add(new Label("osgiLinkLabel", "Manage")));

    }
}
