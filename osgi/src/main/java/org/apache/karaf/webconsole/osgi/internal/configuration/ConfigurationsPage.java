package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationsPage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationsPage() {

        Configuration[] configurations = new Configuration[0];
        try {
            configurations = configurationAdmin.listConfigurations(null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        IModel<String> pid = Model.of("pid");

        List<IColumn<Configuration>> columns = new ArrayList<IColumn<Configuration>>();
        columns.add(new PropertyColumn<Configuration>(pid, "pid"));

        add(new DefaultDataTable<Configuration>("configurations", columns, new ConfigurationProvider(configurations), 20));

    }

}
