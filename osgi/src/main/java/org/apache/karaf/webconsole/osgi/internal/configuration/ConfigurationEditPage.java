package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.IOException;
import java.util.Map;

import org.apache.karaf.webconsole.core.form.MapEditForm;
import org.apache.karaf.webconsole.core.table.map.MapDataProvider;
import org.apache.karaf.webconsole.core.table.map.MapDataTable;
import org.apache.karaf.webconsole.core.util.DictionaryUtils;
import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationModel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/configuration/edit")
public class ConfigurationEditPage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;
    private String pid;

    public ConfigurationEditPage(PageParameters params) {
        pid = params.getString("pid");

        add(new Label("pid", pid));
        final Configuration configuration = new ConfigurationModel(pid, configurationAdmin).getObject();

        @SuppressWarnings("unchecked")
        Map<String, String> properties = DictionaryUtils.map(configuration.getProperties());
        Map<String, String> system = ConfigurationFilterUtil.filter(properties);
        CompoundPropertyModel<Map<String, String>> formModel = new CompoundPropertyModel<Map<String, String>>(properties);

        MapEditForm<String, String> mapEditForm = new MapEditForm<String, String>("edit", formModel) {
            @Override
            protected void onSubmit() {
                Map<String, String> map = getModelObject();

                try {
                    configuration.update(DictionaryUtils.dictionary(map));

                    Session.get().info("Configuration " + pid + " updated.");
                    RequestCycle.get().setResponsePage(ConfigurationsPage.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mapEditForm.add(new SubmitLink("submit"));
        add(mapEditForm);

        add(new MapDataTable<String, String>("system", new MapDataProvider<String, String>(system), 5));
    }

}
