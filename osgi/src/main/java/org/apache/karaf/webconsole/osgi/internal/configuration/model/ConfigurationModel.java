package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import java.io.IOException;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationModel extends AbstractConfigurationModel<Configuration> {

    private String pid;

    public ConfigurationModel(String pid, ConfigurationAdmin configurationAdmin) {
        super(configurationAdmin);
        this.pid = pid;
    }

    @Override
    protected Configuration load() {
        try {
            return configurationAdmin.getConfiguration(pid);
        } catch (IOException e) {
            throw new ConfigurationNotFoundException(pid, e);
        }
    }

}
