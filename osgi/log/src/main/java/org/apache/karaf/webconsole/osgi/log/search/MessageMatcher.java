package org.apache.karaf.webconsole.osgi.log.search;

import org.apache.karaf.webconsole.osgi.log.Options;
import org.osgi.service.log.LogEntry;

public class MessageMatcher implements Matcher {

    public boolean matches(LogEntry entry, Options options) {
        if (options.getMessageFragment() != null && !options.getMessageFragment().isEmpty()) {
            return entry.getMessage().contains(options.getMessageFragment());
        }
        return true;
    }

}
