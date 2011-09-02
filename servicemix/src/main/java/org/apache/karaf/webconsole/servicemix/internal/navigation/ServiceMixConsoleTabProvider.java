package org.apache.karaf.webconsole.servicemix.internal.navigation;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.servicemix.internal.ServiceMixPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

public class ServiceMixConsoleTabProvider implements ConsoleTabProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Collections.emptyList();
    }

    public Link<Page> getModuleLink(String componentId, String labelId) {
        Link<Page> link = new BookmarkablePageLink<Page>(componentId, ServiceMixPage.class);
        link.add(new Label(labelId, "ServiceMix"));
        return link;
    }

  

}
