package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.webconsole.core.ConsoleTab;

import java.util.HashMap;
import java.util.Map;

public class OsgiConsoleTab implements ConsoleTab {

    private static String osgiHome = "osgi";

	public String getLabel() {
		return "Runtime";
	}

	public Class getModuleHomePage() {
		return HomePage.class;
	}

	public Map<String, Class> getItems() {
		Map<String, Class> map = new HashMap<String, Class>();
		map.put(osgiHome, HomePage.class);
		return map;
	}

}
