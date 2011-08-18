package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class DictionaryDataProvider<K extends Serializable, V extends Serializable> implements IDataProvider<Entry<K, V>> {

    private Map<K, V> map = new LinkedHashMap<K, V>();

    public DictionaryDataProvider(Dictionary<K, V> properties) {
        Enumeration<K> keys = properties.keys();

        while (keys.hasMoreElements()) {
            K key = keys.nextElement();
            map.put(key, properties.get(key));
        }
    }

    public void detach() {
        
    }

    public Iterator<? extends Entry<K, V>> iterator(int first, int count) {
        return map.entrySet().iterator();
    }

    public IModel<Entry<K, V>> model(Entry<K, V> object) {
        return new Model(new SerializableEntry<K, V>(object));
    }

    public int size() {
        return 0;
    }

    class SerializableEntry<K extends Serializable, V extends Serializable> implements Entry<K, V>, Serializable {

        private Entry<K, V> entries;

        public SerializableEntry(Entry<K, V> object) {
            this.entries = object;
        }

        public K getKey() {
            return entries.getKey();
        }

        public V getValue() {
            return entries.getValue();
        }

        public V setValue(V value) {
            return entries.setValue(value);
        }

        
    }

}
