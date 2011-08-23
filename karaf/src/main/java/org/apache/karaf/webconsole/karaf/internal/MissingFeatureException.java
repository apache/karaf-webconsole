package org.apache.karaf.webconsole.karaf.internal;

public class MissingFeatureException extends RuntimeException {

    public MissingFeatureException(String name, String version) {
        super("Feature " + name + " with version " + version + " is no longer available");
    }

}
