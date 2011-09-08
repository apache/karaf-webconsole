package org.apache.karaf.webconsole.osgi.internal.log;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.log.search.Matcher;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;

final class LogEntriesDataProvider extends SortableDataProvider<LogEntry> {

    private LogReaderService logReader;

    private List<LogEntry> entries;

    private Options options;

    private transient List<Matcher> matchers;

    public LogEntriesDataProvider(LogReaderService logReader, Options options, List<Matcher> matchers) {
        this.logReader = logReader;
        this.options = options;
        this.matchers = matchers;
    }

    public Iterator<? extends LogEntry> iterator(int first, int count) {
        return getEntries().subList(first, first + count).iterator();
    }

    private List<LogEntry> getEntries() {
        if (entries == null) {
            entries = new LinkedList<LogEntry>();
            @SuppressWarnings("unchecked")
            Enumeration<LogEntry> logEntries = logReader.getLog();
            while (logEntries.hasMoreElements()) {
                LogEntry entry = logEntries.nextElement();

                boolean matchesAll = true;
                for (Matcher matcher : matchers) {
                    if (!matcher.matches(entry, options)) {
                        matchesAll = false;
                        break;
                    }
                }

                if (matchesAll) entries.add(entry);
            }
        }
        return entries;
    }

    public IModel<LogEntry> model(LogEntry object) {
        return new LogEntryModel(this.logReader, object);
    }

    public int size() {
        return getEntries().size();
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public void detach() {
        entries = null;
    }
}