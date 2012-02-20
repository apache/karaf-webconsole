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
package org.apache.karaf.webconsole.osgi.core.shared;

import org.osgi.framework.Bundle;

public enum State {
    UNINSTALLED(1),
    INSTALLED(2),
    RESOLVED(4),
    STARTING(8),
    STOPPING(16),
    ACTIVE(32),

    UNKNOWN(-1);

    private final int mask;

    private State(int mask) {
        this.mask = mask;
        
    }

    public static State of(int state) {
        for (State enumeration : values()) {
            if (enumeration.mask == state) {
                return enumeration;
            }
        }
        return UNKNOWN;
    }

    public static State of(Bundle bundle) {
        return of(bundle.getState());
    }

}
