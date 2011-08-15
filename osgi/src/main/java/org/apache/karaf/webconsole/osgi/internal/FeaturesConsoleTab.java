package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.webconsole.core.ConsoleTab;

import java.util.HashMap;
import java.util.Map;

public class FeaturesConsoleTab implements ConsoleTab {

    private static final long serialVersionUID = 1L;
    private static String featuresList = "features.list";
    private static String featuresRepositories = "features.repositories";

    public String getLabel() {
        return "Features";
    }

    public Class getModuleHomePage() {
        return FeaturesPage.class;
    }

    public Map<String, Class> getItems() {
        Map<String, Class> features = new HashMap<String, Class>();
        features.put(featuresList, FeaturesPage.class);
        features.put(featuresRepositories, RepositoriesPage.class);
        return features;
    }

}
