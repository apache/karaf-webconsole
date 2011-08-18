package org.apache.karaf.webconsole.karaf.internal.feature;

import org.apache.karaf.features.BundleInfo;
import org.apache.karaf.features.ConfigFileInfo;
import org.apache.karaf.features.Feature;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class extending Feature class to provide additional info
 * like Repository name, ...
 */
class ExtendedFeature implements Feature, Serializable {

    public enum State {
        INSTALLED, UNINSTALLED;

        @Override
        public String toString() {
            //only capitalize the first letter
            String s = super.toString();
            return s.substring(0, 1) + s.substring(1).toLowerCase();
        }
    }

    protected State state;
    protected String repository;
    protected Feature feature;


    //
    // Constructors
    //

    public ExtendedFeature(State state, String repository, Feature feature) {
        this.state = state;
        this.repository = repository;
        this.feature = feature;
    }


    //
    // Feature interface
    //


    public List<BundleInfo> getBundles() {
        return this.feature.getBundles();
    }


    public Map<String, Map<String, String>> getConfigurations() {
        return this.feature.getConfigurations();
    }

    public List<ConfigFileInfo> getConfigurationFiles() {
        return this.feature.getConfigurationFiles();
    }

    public String getId() {
        return this.feature.getId();
    }


    public String getName() {
        return this.feature.getName();
    }


    public String getVersion() {
        return this.feature.getVersion();
    }

    public String getResolver() {
        return this.feature.getResolver();
    }

    public List<Feature> getDependencies() {
        return null;
    }

    public String getDescription() {
        return this.feature.getDescription();
    }

    public String getDetails() {
        return this.feature.getDetails();
    }


    //
    // Additional methods
    //


    public String getRepository() {
        return this.repository;
    }


    public State getState() {
        return this.state;
    }

    public int getStartLevel() {
        return 0;
    }
}
