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
        System.out.println("How you did that?");
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
