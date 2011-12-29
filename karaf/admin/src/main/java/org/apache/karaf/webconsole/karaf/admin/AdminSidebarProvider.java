package org.apache.karaf.webconsole.karaf.admin;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.karaf.webconsole.karaf.admin.create.CreateInstancePage;
import org.apache.karaf.webconsole.karaf.admin.list.InstancesPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class AdminSidebarProvider implements SidebarProvider {

    @SuppressWarnings("unchecked")
    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Arrays.asList(
            createPageLink(componentId, labelId, "Create instance", CreateInstancePage.class)
        );
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return createPageLink(linkId, labelId, "Instances", InstancesPage.class);
    }

    public List<WidgetProvider> getWidgetProviders() {
        return Collections.emptyList();
    }

}
