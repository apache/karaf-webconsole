package org.apache.karaf.webconsole.osgi.internal.navigation;

import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.karaf.webconsole.osgi.internal.bundle.BundlesPage;
import org.apache.wicket.Page;

public class OsgiConsoleTab implements ConsoleTab {

    public String getLabel() {
        return "osgi";
    }

    public Class<? extends Page> getModuleHomePage() {
        return BundlesPage.class;
    }

    public Map<String, Class<? extends Page>> getItems() {
        return new HashMap<String, Class<? extends Page>>();
    }

}
