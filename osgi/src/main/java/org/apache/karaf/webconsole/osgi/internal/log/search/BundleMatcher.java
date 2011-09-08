package org.apache.karaf.webconsole.osgi.internal.log.search;

import org.apache.karaf.webconsole.osgi.internal.log.Options;
import org.osgi.service.log.LogEntry;

public class BundleMatcher implements Matcher {

    public boolean matches(LogEntry entry, Options options) {
        if (options.getBundleNameFragment() != null && !options.getBundleNameFragment().isEmpty()) {
            return entry.getBundle().getSymbolicName().contains(options.getBundleNameFragment());
        }
        return true;
    }

}
