package org.apache.karaf.webconsole.karaf.admin.navigation;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.NavigationProvider;
import org.apache.karaf.webconsole.karaf.admin.list.InstancesPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class AdminNavigationProvider implements NavigationProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        List<Link<Page>> links = new ArrayList<Link<Page>>();
        links.add(createPageLink(componentId, labelId, "Instances", InstancesPage.class));
        return links;
    }

}
