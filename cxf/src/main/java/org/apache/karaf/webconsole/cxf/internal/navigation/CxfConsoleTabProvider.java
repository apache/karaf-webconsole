package org.apache.karaf.webconsole.cxf.internal.navigation;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.karaf.webconsole.cxf.internal.services.CxfServicesPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class CxfConsoleTabProvider implements ConsoleTabProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Collections.emptyList();
    }

    public Link<Page> getModuleLink(String componentId, String labelId) {
        return LinkUtils.createPageLink(componentId, labelId, "CXF", CxfServicesPage.class);
    }


}
