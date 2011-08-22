package org.apache.karaf.webconsole.osgi.internal.configuration.model;

public class ConfigurationNotFoundException extends RuntimeException {

    public ConfigurationNotFoundException(String pid) {
        super(pid);
    }

    public ConfigurationNotFoundException(String pid, Throwable cause) {
        super(pid, cause);
    }
}
