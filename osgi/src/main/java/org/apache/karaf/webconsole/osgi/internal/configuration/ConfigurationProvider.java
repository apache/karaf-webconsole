package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

public class ConfigurationProvider extends SortableDataProvider<Configuration> {

    private final Configuration[] configurations;

    public ConfigurationProvider(Configuration[] configurations) {
        this.configurations = configurations;
    }

    public Iterator<? extends Configuration> iterator(int arg0, int arg1) {
        return Arrays.asList(Arrays.copyOfRange(configurations, arg0, arg1)).iterator();
    }

    public IModel<Configuration> model(Configuration arg0) {
        return new CompoundPropertyModel<Configuration>(arg0);
    }

    public int size() {
        return configurations.length;
    }

}
