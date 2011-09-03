package org.apache.karaf.webconsole.core.navigation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class ExtendableSidebarProvider implements SidebarProvider {

    private Collection<NavigationProvider> navigationProviders;
    private Collection<WidgetProvider> widgetProviders;
    private SidebarProvider base;

    public ExtendableSidebarProvider(SidebarProvider base) {
        this.base = base;
    }

    public List<Link<Page>> getItems(String componentId, String labelId) {
        // create new list because instance returned from base provider may be read only..
        List<Link<Page>> items = new ArrayList<Link<Page>>(base.getItems(componentId, labelId));

        for (NavigationProvider provider : navigationProviders) {
            items.addAll(provider.getItems(componentId, labelId));
        }

        return items;
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return base.getMasterPageLink(linkId, labelId);
    }

    public List<WidgetProvider> getWidgetProviders() {
        List<WidgetProvider> list = new ArrayList<WidgetProvider>(base.getWidgetProviders());
        list.addAll(widgetProviders);
        return list;
    }

    public void setNavigationProviders(Collection<NavigationProvider> navigationProviders) {
        this.navigationProviders = navigationProviders;
    }


    public void setWidgetProviders(Collection<WidgetProvider> widgetProviders) {
        this.widgetProviders = widgetProviders;
    }
}
