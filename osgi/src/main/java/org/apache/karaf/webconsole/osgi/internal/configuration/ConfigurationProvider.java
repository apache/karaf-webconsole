package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationProvider extends SortableDataProvider<Configuration> {

    private Configuration[] configurations = new Configuration[0];
    private final ConfigurationAdmin configurationAdmin;

    public ConfigurationProvider(ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
        try {
            this.configurations = configurationAdmin.listConfigurations(null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Iterator<? extends Configuration> iterator(int arg0, int arg1) {
        return Arrays.asList(Arrays.copyOfRange(configurations, arg0, arg1)).iterator();
    }

    public IModel<Configuration> model(Configuration arg0) {
        return new ConfigurationModel(arg0.getPid(), configurationAdmin);
    }

    public int size() {
        return configurations.length;
    }

}
