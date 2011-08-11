package org.apache.karaf.webconsole.osgi.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.ConsoleTab;

public class OsgiConsoleTab implements ConsoleTab {

	public String getLabel() {
		return "Runtime";
	}

	public Class getModuleHomePage() {
		return HomePage.class;
	}

	public Map<String, Class> getItems() {
		Map<String, Class> map = new HashMap<String, Class>();
		map.put("OSGi", HomePage.class);
		return map;
	}

}
