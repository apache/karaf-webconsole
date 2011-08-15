package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.webconsole.core.ConsoleTab;

import java.util.HashMap;
import java.util.Map;

public class FeaturesConsoleTab implements ConsoleTab {

    private static final long serialVersionUID = 1L;

    public String getLabel() {
        return "Features";
    }

    public Class getModuleHomePage() {
        return FeaturesPage.class;
    }

    //new ResourceModel("features.console.list").getObject().toString()
    public Map<String, Class> getItems() {
        Map<String, Class> features = new HashMap<String, Class>();
        features.put("Features list", FeaturesPage.class);
        features.put("Features repositories", RepositoriesPage.class);
        return features;
    }

}
