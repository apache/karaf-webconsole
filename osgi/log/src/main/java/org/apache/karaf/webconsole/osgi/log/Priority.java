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

import static org.osgi.service.log.LogService.LOG_DEBUG;
import static org.osgi.service.log.LogService.LOG_ERROR;
import static org.osgi.service.log.LogService.LOG_INFO;
import static org.osgi.service.log.LogService.LOG_WARNING;

import org.osgi.service.log.LogEntry;

/**
 * Log priority mapping.
 */
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
