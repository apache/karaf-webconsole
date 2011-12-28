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
package org.apache.karaf.webconsole.osgi.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class which removes system properties from configuration dictionary.
 */
public abstract class ConfigurationFilterUtil {

    public static <K extends Object, V> Map<String, V> filter(Map<K, V> map) {
        Map<String, V> system = new HashMap<String, V>();
        system.put("service.pid", map.remove("service.pid"));
        system.put("service.factoryPid", map.remove("service.factoryPid"));
        system.put("service.bundleLocation", map.remove("service.bundleLocation"));
        system.put("felix.fileinstall.filename", map.remove("felix.fileinstall.filename"));

        return system;
    }
}
