package org.apache.karaf.webconsole.karaf.admin.model;

import java.io.Serializable;

import org.apache.karaf.admin.InstanceSettings;

public class InstanceSettingsExt extends InstanceSettings implements Serializable {

    public InstanceSettingsExt() {
        super(0, 0, 0, "", "", null, null);
    }

}
