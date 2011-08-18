package org.apache.karaf.webconsole.core.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.Page;

public class SystemConsoleTab implements ConsoleTab, Serializable {

    public String getLabel() {
        return "dashboard";
    }

    public Class<? extends Page> getModuleHomePage() {
        return DashboardPage.class;
    }

    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> map = new HashMap<String, Class<? extends Page>>();
        map.put("dashboard", DashboardPage.class);
        return map;
    }

}
