package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationModel;
import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationNotFoundException;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationProvider extends SortableDataProvider<Configuration> {

    private Configuration[] configurations = new Configuration[0];
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationProvider(ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
        try {
            this.configurations = configurationAdmin.listConfigurations(null);
        } catch (Exception e) {
            throw new ConfigurationNotFoundException(null, e);
        }
    }

    public Iterator<? extends Configuration> iterator(int from, int count) {
        return Arrays.asList(Arrays.copyOfRange(configurations, from, count)).iterator();
    }

    public IModel<Configuration> model(Configuration object) {
        return new ConfigurationModel(object, configurationAdmin);
    }

    public int size() {
        return configurations.length;
    }

}
