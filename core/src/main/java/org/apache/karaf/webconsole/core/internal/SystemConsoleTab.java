package org.apache.karaf.webconsole.core.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.ConsoleTab;

public class SystemConsoleTab implements ConsoleTab, Serializable {

    private static final long serialVersionUID = -7381914604367435106L;

    public String getLabel() {
        return "Dashboard";
    }

    public Class getModuleHomePage() {
        return DashboardPage.class;
    }

    public Map<String, Class> getItems() {
        Map<String, Class> map = new HashMap<String, Class>();
        map.put("Dashboard", DashboardPage.class);
        return map;
    }

}
