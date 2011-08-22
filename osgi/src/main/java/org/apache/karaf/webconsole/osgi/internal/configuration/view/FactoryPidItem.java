package org.apache.karaf.webconsole.osgi.internal.configuration.view;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

public class FactoryPidItem extends OddEvenItem<Configuration> {

    public FactoryPidItem(String id, int index, IModel<Configuration> model) {
        super(id, index, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.append("class", "factory", " ");
    }

}
