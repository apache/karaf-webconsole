package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationModel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
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

        try {
            add(form(configurationAdmin.getConfiguration(pid)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Form form(Configuration configuration) {
        CompoundPropertyModel<Configuration> model = new CompoundPropertyModel<Configuration>(null);
        model.setChainedModel(new ConfigurationModel(pid, configurationAdmin));

        Form<Configuration> form = new Form<Configuration>("edit", model) {
            @Override
            public void process(IFormSubmittingComponent submittingComponent) {
                System.out.println("--> " + getModelObject());
            }
        };

        form.add(new TextField<String>("pid") {
            @Override
            public boolean isEnabled() {
                return false;
            }
        });

        Dictionary<String, Serializable> properties = configuration.getProperties();
        properties.get("service.pid");
        properties.get("service.factory");

        Map<String, Serializable> map = new LinkedHashMap<String, Serializable>();
        Enumeration<String> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, properties.get(key));
        }
        List<Entry<String, Serializable>> list = new ArrayList<Entry<String, Serializable>>(map.entrySet());

        form.add(new ListView<Entry<String, Serializable>>("properties", new ListModel<Entry<String, Serializable>>(list)) {
            @Override
            protected void populateItem(ListItem<Entry<String, Serializable>> item) {
                item.add(new Label("key", item.getModelObject().getKey()));
                item.add(new TextField<String>("value", new Model(item.getModelObject().getValue())));
            }
            @Override
            public boolean getReuseItems() {
                return true;
            }
        });

        form.add(new Button("submit"));
        return form;
    }

}
