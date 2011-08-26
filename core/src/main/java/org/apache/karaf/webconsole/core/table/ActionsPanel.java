package org.apache.karaf.webconsole.core.table;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

/**
 * Actions panel used to render actions in data table.
 */
@SuppressWarnings("rawtypes")
public class ActionsPanel<T> extends Panel {

    public ActionsPanel(String componentId, IModel<T> model) {
        super(componentId, model);

        add(CSSPackageResource.getHeaderContribution(ActionsPanel.class, "actions.css"));

        final List<Link> links = getLinks(model.getObject(), "action");
        add(new ListView<Link>("actions", new ListModel<Link>(links)) {
            @Override
            protected void populateItem(ListItem<Link> item) {
                item.addOrReplace(item.getModelObject());

                if (item.getIndex() == 0) {
                    item.add(new SimpleAttributeModifier("class", "first"));
                } else if (item.getIndex() -1 == links.size()) {
                    item.add(new SimpleAttributeModifier("class", "last"));
                } else {
                    item.add(new SimpleAttributeModifier("class", "node"));
                }
            }
        });
    }

    protected List<Link> getLinks(T object, String id) {
        return Collections.emptyList();
    }

    
}
