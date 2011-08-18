package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
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
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        final boolean empty = configurations.length == 0;

        IModel<String> pid = Model.of("pid");

        List<IColumn<Configuration>> columns = new ArrayList<IColumn<Configuration>>();
        columns.add(new PropertyColumn<Configuration>(pid, "pid"));
        columns.add(new AbstractColumn<Configuration>(Model.of("operations")) {
            public void populateItem(Item<ICellPopulator<Configuration>> cellItem, String componentId, IModel<Configuration> model) {
                cellItem.add(new Label("info", "operation"));
            }
        });

        add(new DefaultDataTable<Configuration>("configurations", columns, new ConfigurationProvider(configurations), 20));

        add(new Label("noData", "No configuration found. " + (configurationAdmin == null ? "Configuration Admin not found" : "")) {
            @Override
            public boolean isVisible() {
                return empty;
            }
        });
    }

}
