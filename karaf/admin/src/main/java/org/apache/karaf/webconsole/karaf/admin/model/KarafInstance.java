package org.apache.karaf.webconsole.karaf.admin.model;

import java.io.Serializable;

public class KarafInstance implements Serializable {

    private String name;

    private InstanceSettingsExt settings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstanceSettingsExt getSettings() {
        return settings;
    }

    public void setSettings(InstanceSettingsExt settings) {
        this.settings = settings;
    }

}
