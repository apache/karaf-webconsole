package org.apache.karaf.webconsole.osgi.internal.log.search;

import org.apache.karaf.webconsole.osgi.internal.log.Options;
import org.osgi.service.log.LogEntry;

public class PriorityMatcher implements Matcher {

    public boolean matches(LogEntry entry, Options options) {
        return options.getPriority().getLevel() >= entry.getLevel();
    }

}
