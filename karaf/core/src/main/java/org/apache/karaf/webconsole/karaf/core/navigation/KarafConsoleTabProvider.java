package org.apache.karaf.webconsole.karaf.core.navigation;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.karaf.core.KarafOverviewPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class KarafConsoleTabProvider implements ConsoleTabProvider {

    public Link<Page> getModuleLink(String componentId, String labelId) {
        return createPageLink(componentId, labelId, "Karaf", KarafOverviewPage.class);
    }

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Collections.emptyList();
    }

}
