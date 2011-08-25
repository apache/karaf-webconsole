package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class which removes system properties from configuration dictionary.
 */
public abstract class ConfigurationFilterUtil {

    public static <K extends Object, V> Map<String, V> filter(Map<K, V> map) {
        Map<String, V> system = new HashMap<String, V>();
        system.put("service.pid", map.remove("service.pid"));
        system.put("service.factoryPid", map.remove("service.factoryPid"));
        system.put("service.bundleLocation", map.remove("service.bundleLocation"));
        system.put("felix.fileinstall.filename", map.remove("felix.fileinstall.filename"));

        return system;
    }
}
