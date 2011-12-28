package org.apache.karaf.webconsole.osgi.log;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.NavigationProvider;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class LogNavigationProvider implements NavigationProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        List<Link<Page>> links = new ArrayList<Link<Page>>();
        links.add(createPageLink(componentId, labelId, "Logs", LogsPage.class));
        return links;
    }

}
