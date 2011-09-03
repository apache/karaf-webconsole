package org.apache.karaf.webconsole.core.navigation;

import java.util.List;

import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

/**
 * Sidebar provider is responsible for enriching sidebar, it have a master page
 * link and set od widget providers.
 */
public interface SidebarProvider extends NavigationProvider {

    /**
     * Gets master page link - default page which should be on top of all.
     * 
     * @param linkId Link element id.
     * @param labelId Label element id.
     * @return Master page link.
     */
    Link<Page> getMasterPageLink(String linkId, String labelId);

    /**
     * Gets widget providers for given sidebar.
     * 
     * @return
     */
    List<WidgetProvider> getWidgetProviders();

}
