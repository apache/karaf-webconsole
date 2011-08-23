package org.apache.karaf.webconsole.karaf.internal;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.wicket.model.LoadableDetachableModel;

public class FeatureModel extends LoadableDetachableModel<Feature> {

    private FeaturesService service;
    private String version;
    private String name;

    public FeatureModel(FeaturesService service, Feature object) {
        this.service = service;
        this.name = object.getName();
        this.version = object.getVersion();
    }

    @Override
    protected Feature load() {
        Feature[] features = new Feature[0];

        try {
            features = service.listFeatures();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Feature feature : features) {
            if (name.equals(feature.getName()) && version.equals(feature.getVersion())) {
                return feature;
            }
        }

        throw new MissingFeatureException(name, version);
    }
}
