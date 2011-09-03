package org.apache.karaf.webconsole.servicemix.internal.sidebar;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.karaf.webconsole.servicemix.internal.EndpointsPage;
import org.apache.karaf.webconsole.servicemix.internal.TrackNmrPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;


public class ServicemixSidebar implements SidebarProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Arrays.asList(
            createPageLink(componentId, labelId, "Exchanges", TrackNmrPage.class)
        );
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return createPageLink(linkId, labelId, "Endpoints", EndpointsPage.class);
    }

    public List<WidgetProvider> getWidgetProviders() {
        return Collections.emptyList();
    }

}
