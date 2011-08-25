package org.apache.karaf.webconsole.core.table.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class MapDataProvider<K, V> extends SortableDataProvider<Entry<K, V>> {

    private final Map<K, V> map;

    public MapDataProvider(Map<K, V> map) {
        this.map = map;
    }

    public Iterator<? extends Entry<K, V>> iterator(int first, int count) {
        return new ArrayList<Entry<K, V>>(map.entrySet()).subList(first, first + count).iterator();
    }

    public int size() {
        return map.size();
    }

    public IModel<Entry<K, V>> model(Entry<K, V> object) {
        return new EntryModel<K, V>(object);
    }

}
