package org.apache.karaf.webconsole.core.table;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

public class PropertyColumnExt<T> extends PropertyColumn<T> {

    public PropertyColumnExt(String label, String property) {
        super(Model.of(label), property);
    }

    public PropertyColumnExt(String property) {
        this(property, property);
    }

}
