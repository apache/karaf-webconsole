package org.apache.karaf.webconsole.servicemix.internal.navigation;

import java.util.Collections;
import java.util.Map;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.karaf.webconsole.servicemix.internal.ServiceMixPage;
import org.apache.wicket.Page;

public class ServiceMixConsoleTab implements ConsoleTab {

    public Map<String, Class<? extends Page>> getItems() {
        return Collections.emptyMap();
    }

    public String getLabel() {
        return "servicemix";
    }

    public Class<? extends Page> getModuleHomePage() {
        return ServiceMixPage.class;
    }

  

}
