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
package org.apache.karaf.webconsole.core.util;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Utility class to work with dictionaries.
 */
public abstract class DictionaryUtils {

    private DictionaryUtils() {
        // utility class, no public constructor
    }

    /**
     * Create an map from given dictionary.
     * 
     * @param <K> Type of the key.
     * @param <V> Type of the value.
     * @param dictionary Dictionary to be converted.
     * @return Map with copy of elements from dictionary.
     */
    public static <K, V> Map<K, V> map(Dictionary<K, V> dictionary) {
        HashMap<K, V> map = new HashMap<K, V>();

        Enumeration<K> keys = dictionary.keys();
        while (keys.hasMoreElements()) {
            K key = keys.nextElement();
            map.put(key, dictionary.get(key));
        }

        return map;
    }

    /**
     * Create an dictionary from map.
     * 
     * @param <K> Type of the key.
     * @param <V> Type of the value.
     * @param map Map to be converted.
     * @return Dictionary with copy of elements from map.
     */
    public static <K, V> Dictionary<K, V> dictionary(Map<K, V> map) {
        return new Hashtable<K, V>(map);
    }
}
