package org.apache.karaf.webconsole.osgi.log.search;

import org.apache.karaf.webconsole.osgi.log.Options;
import org.osgi.service.log.LogEntry;

public interface Matcher {

    boolean matches(LogEntry entry, Options options);

}
