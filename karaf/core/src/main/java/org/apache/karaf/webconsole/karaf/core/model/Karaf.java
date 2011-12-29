package org.apache.karaf.webconsole.karaf.core.model;

import java.io.Serializable;

public class Karaf implements Serializable {

    public String getVersion() {
        return System.getProperty("karaf.version");
    }

    public String getHome() {
        return System.getProperty("karaf.home");
    }

    public String getBase() {
        return System.getProperty("karaf.base");
    }

    public String getData() {
        return System.getProperty("karaf.data");
    }

    public String getInstances() {
        return System.getProperty("karaf.instances");
    }

    public Boolean isLocalConsole() {
        return Boolean.getBoolean("karaf.startLocalConsole");
    }

    public Boolean isRemoteShell() {
        return Boolean.getBoolean("karaf.startRemoteShell");
    }

}
