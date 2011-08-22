package org.apache.karaf.webconsole.osgi.internal.configuration.view;

import java.io.IOException;

import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationsPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationNotFoundException;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

/**
 * Simple form responsible for removal of the configuration.
 */
public class ConfigurationRemoveForm extends Form<Configuration> {

    public ConfigurationRemoveForm(String id, IModel<Configuration> model) {
        super(id, model);

        add(new Button("submit"));
    }

    @Override
    public void process(IFormSubmittingComponent submittingComponent) {
        try {
            Configuration cfg = getModelObject();
            String pid = cfg.getPid();
            cfg.delete();

            getSession().info("Configuration " + pid + " was removed");
            getRequestCycle().setResponsePage(ConfigurationsPage.class);
        } catch (IOException e) {
            throw new ConfigurationNotFoundException(getModelObject().getPid(), e);
        }
    }

}