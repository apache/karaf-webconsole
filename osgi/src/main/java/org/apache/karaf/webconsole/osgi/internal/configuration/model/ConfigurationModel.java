package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import java.io.IOException;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationModel extends LoadableDetachableModel<Configuration> {

    private String pid;
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationModel(String pid, ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
        this.pid = pid;
    }

    public ConfigurationModel(Configuration configuration, ConfigurationAdmin configurationAdmin) {
        super(configuration);
        this.configurationAdmin = configurationAdmin;
        this.pid = configuration.getPid();
    }

    @Override
    protected Configuration load() {
        try {
            return configurationAdmin.getConfiguration(pid);
        } catch (IOException e) {
            throw new ConfigurationNotFoundException(pid, e);
        }
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        setObject(null);
    }
}
