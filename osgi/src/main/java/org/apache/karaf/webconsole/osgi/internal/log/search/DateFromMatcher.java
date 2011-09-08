package org.apache.karaf.webconsole.osgi.internal.log.search;

import org.apache.karaf.webconsole.osgi.internal.log.Options;
import org.osgi.service.log.LogEntry;

public class DateFromMatcher implements Matcher {

    public boolean matches(LogEntry entry, Options options) {
        if (options.getDateFrom() != null && options.getDateFrom() > 0) {
            return entry.getTime() >= options.getDateFrom();
        }
        return true;
    }

}
