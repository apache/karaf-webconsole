package org.apache.karaf.webconsole.core.table.map;

import java.util.Map.Entry;

import org.apache.wicket.model.IModel;

public class EntryModel<K, V> implements IModel<Entry<K, V>> {

    private Entry<K, V> object;

    public EntryModel(Entry<K, V> object) {
        this.object = object;
    }

    public void detach() {
        this.object = null;
    }

    public Entry<K, V> getObject() {
        return object;
    }

    public void setObject(Entry<K, V> object) {
        this.object = object;
    }

}
