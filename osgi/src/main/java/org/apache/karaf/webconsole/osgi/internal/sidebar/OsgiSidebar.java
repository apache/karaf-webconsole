package org.apache.karaf.webconsole.osgi.internal.sidebar;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.karaf.webconsole.osgi.internal.bundle.BundlesPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationsPage;
import org.apache.karaf.webconsole.osgi.internal.event.EventsPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class OsgiSidebar implements SidebarProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Arrays.asList(
            createPageLink(componentId, labelId, "Configuration", ConfigurationsPage.class),
            createPageLink(componentId, labelId, "Events", EventsPage.class)
        );
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return createPageLink(linkId, labelId, "Bundles", BundlesPage.class);
    }

    public List<WidgetProvider> getWidgetProviders() {
        return Collections.emptyList();
    }

}
