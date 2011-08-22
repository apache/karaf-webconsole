package org.apache.karaf.webconsole.osgi.internal.configuration;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.view.ConfigurationstDataTable;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.ConfigurationAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/configuration")
public class ConfigurationsPage extends OsgiPage {

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;

    public ConfigurationsPage() {
        add(CSSPackageResource.getHeaderContribution(ConfigurationsPage.class, "configurations.css"));

        add(new ConfigurationstDataTable("configurations", new ConfigurationProvider(configurationAdmin), 20));

    }

}
