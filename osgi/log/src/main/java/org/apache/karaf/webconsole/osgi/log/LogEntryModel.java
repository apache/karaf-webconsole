package org.apache.karaf.webconsole.osgi.log;

import java.util.Collections;
import java.util.Enumeration;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;

public class LogEntryModel extends LoadableDetachableModel<LogEntry> {

    private int hashCode;
    private LogReaderService logReader;

    public LogEntryModel(LogReaderService logReader, LogEntry object) {
        super(object);
        this.logReader = logReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected LogEntry load() {
        for (LogEntry entry : Collections.list((Enumeration<LogEntry>) logReader.getLog())) {
            if (hashCode == entry.hashCode()) {
                return entry;
            }
        }
        return null;
    }
}