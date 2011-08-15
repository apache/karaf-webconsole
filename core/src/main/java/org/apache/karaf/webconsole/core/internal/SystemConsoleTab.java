package org.apache.karaf.webconsole.core.internal;

import org.apache.karaf.webconsole.core.ConsoleTab;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SystemConsoleTab implements ConsoleTab, Serializable {

    private static final long serialVersionUID = -7381914604367435106L;
    private static String dashBoard = "dashboard";

    public String getLabel() {
        return "Dashboard";
    }

    public Class getModuleHomePage() {
        return DashboardPage.class;
    }

    public Map<String, Class> getItems() {
        Map<String, Class> map = new HashMap<String, Class>();
        map.put(dashBoard, DashboardPage.class);
        return map;
    }

}
