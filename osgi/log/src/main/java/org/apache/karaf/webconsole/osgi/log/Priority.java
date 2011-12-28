package org.apache.karaf.webconsole.osgi.log;

import static org.osgi.service.log.LogService.LOG_DEBUG;
import static org.osgi.service.log.LogService.LOG_ERROR;
import static org.osgi.service.log.LogService.LOG_INFO;
import static org.osgi.service.log.LogService.LOG_WARNING;

import org.osgi.service.log.LogEntry;


public enum Priority {

    Error(LOG_ERROR),
    Warning(LOG_WARNING),
    Info(LOG_INFO),
    Debug(LOG_DEBUG),

    Any(5);

    private final int level;

    private Priority(int priority) {
        this.level = priority;
    }

    public int getLevel() {
        return level;
    }

    public static Priority valueOf(LogEntry entry) {
        for (Priority priority : values()) {
            if (priority.level == entry.getLevel()) {
                return priority;
            }
        }

        return Any;
    }

}
