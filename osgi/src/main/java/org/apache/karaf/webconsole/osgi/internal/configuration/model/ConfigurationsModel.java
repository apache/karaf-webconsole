package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationsModel extends AbstractConfigurationModel<Configuration[]> {

    public ConfigurationsModel(ConfigurationAdmin configurationAdmin) {
        super(configurationAdmin);
    }

    @Override
    protected Configuration[] load() {
        try {
            return configurationAdmin.listConfigurations(null);
        } catch (Exception e) {
            throw new ConfigurationNotFoundException(null, e);
        }
    }

}
