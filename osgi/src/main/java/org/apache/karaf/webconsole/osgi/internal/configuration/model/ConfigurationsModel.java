package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationsModel extends LoadableDetachableModel<Configuration[]> {

    private final ConfigurationAdmin configurationAdmin;

    public ConfigurationsModel(ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
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
