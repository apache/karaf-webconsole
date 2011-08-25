package org.apache.karaf.webconsole.core.form;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public abstract class MapEditForm<K, V> extends Form<Map<K, V>> {

    public MapEditForm(String id, CompoundPropertyModel<Map<K, V>> model) {
        super(id, model);

        RepeatingView repeatingView = new RepeatingView("entries");

        for (K key : model.getObject().keySet()) {
            IModel<V> bind = model.bind("" + key);
            repeatingView.add(populateItem(repeatingView.newChildId(), key, bind));
        }
        add(repeatingView);
    }

    @SuppressWarnings("unchecked")
    protected Component populateItem(String componentId, K key, IModel<V> value) {
        LabelBorder border = new LabelBorder(componentId);
        border.add(new Label("label", "" + key));
        border.add(new TextField<V>("value", value, (Class<V>) value.getObject().getClass()));
        return border;
    }
}
