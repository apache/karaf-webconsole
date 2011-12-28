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

package org.apache.karaf.webconsole.osgi.scr;

import org.apache.felix.scr.Component;

/**
 * Utility class to extract component state from int mask.
 */
public class ScrUtils {

    @SuppressWarnings("deprecation")
    public static String getState(Component component) {
        switch (component.getState()) {
            case Component.STATE_DISABLED:
                return "disabled";
            case Component.STATE_ENABLING:
                return "enabling";
            case Component.STATE_ENABLED:
                return "enabled";
            case Component.STATE_UNSATISFIED:
                return "unsatisfied";
            case Component.STATE_ACTIVATING:
                return "activating";
            case Component.STATE_ACTIVE:
                return "active";
            case Component.STATE_REGISTERED:
                return "registered";
            case Component.STATE_FACTORY:
                return "factory";
            case Component.STATE_DEACTIVATING:
                return "deactivating";
            case Component.STATE_DISABLING:
                return "disabling";
            case Component.STATE_DISPOSING:
                return "disposing";
            case Component.STATE_DISPOSED:
                return "disposed";
            default:
                return "" + component.getState();
        }
    }

}
