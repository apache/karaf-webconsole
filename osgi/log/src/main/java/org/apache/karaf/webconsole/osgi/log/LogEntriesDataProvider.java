/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.osgi.log;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.karaf.webconsole.osgi.log.search.Matcher;
import org.apache.wicket.model.IModel;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;

/**
 * Data provider for log table.
 */
final class LogEntriesDataProvider extends BaseDataProvider<LogEntry> {

    private static final long serialVersionUID = 1L;

    private LogReaderService logReader;

    private List<LogEntry> entries;

    private Options options;

    private List<Matcher> matchers;

    public LogEntriesDataProvider(LogReaderService logReader, Options options, List<Matcher> matchers) {
        this.logReader = logReader;
        this.options = options;
        this.matchers = matchers;
    }

    public Iterator<? extends LogEntry> iterator(long first, long count) {
        return getEntries().subList((int) first, (int) first + (int) count).iterator();
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

    public long size() {
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