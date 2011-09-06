package org.apache.karaf.webconsole.camel.internal.navigation;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.camel.internal.context.CamelContextsPage;
import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class CamelConsoleTabProvider implements ConsoleTabProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Collections.emptyList();
    }

    public Link<Page> getModuleLink(String componentId, String labelId) {
        return LinkUtils.createPageLink(componentId, labelId, "Camel", CamelContextsPage.class);
    }

}
