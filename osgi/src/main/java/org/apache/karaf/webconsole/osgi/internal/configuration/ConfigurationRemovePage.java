package org.apache.karaf.webconsole.osgi.internal.configuration;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.model.ConfigurationModel;
import org.apache.karaf.webconsole.osgi.internal.configuration.view.ConfigurationRemoveForm;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Configuration remove page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/configuration/remove")
public class ConfigurationRemovePage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;
    private String pid;

    public ConfigurationRemovePage(PageParameters params) {
        pid = params.getString("pid");

        add(new Label("pid", pid));
        add(new ConfigurationRemoveForm("remove", new ConfigurationModel(pid, configurationAdmin)));
    }

}
