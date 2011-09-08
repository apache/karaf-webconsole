package org.apache.karaf.webconsole.osgi.internal.log.search;

import org.apache.karaf.webconsole.osgi.internal.log.Options;
import org.osgi.service.log.LogEntry;

public class DateToMatcher implements Matcher {

    public boolean matches(LogEntry entry, Options options) {
        if (options.getDateTo() != null && options.getDateTo() > 0) {
            return entry.getTime() <= options.getDateTo();
        }
        return true;
    }

}
