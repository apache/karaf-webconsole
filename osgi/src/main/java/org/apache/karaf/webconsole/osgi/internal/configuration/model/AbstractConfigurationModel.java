package org.apache.karaf.webconsole.osgi.internal.configuration.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Base class for configuration models.
 */
public abstract class AbstractConfigurationModel<T> extends LoadableDetachableModel<T> {

    /**
     * Configuration admin.
     */
    protected ConfigurationAdmin configurationAdmin;

    public AbstractConfigurationModel(ConfigurationAdmin configurationAdmin) {
        this.configurationAdmin = configurationAdmin;
    }

}