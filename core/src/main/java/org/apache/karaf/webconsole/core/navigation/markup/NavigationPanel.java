package org.apache.karaf.webconsole.core.navigation.markup;

import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Component responsible for rendering top navigation in webconsole.
 */
public class NavigationPanel extends Panel {

    public NavigationPanel(String id, IModel<List<ConsoleTab>> model) {
        super(id);

        add(new ListView<ConsoleTab>("tabs", model) {
            @Override
            protected void populateItem(ListItem<ConsoleTab> item) {
                final ConsoleTab tab = item.getModelObject();

                item.add(new BookmarkablePageLink("moduleLink", tab.getModuleHomePage()).add(new Label("moduleLabel", tab.getLabel())));

                List<String> subItems = new LinkedList<String>(tab.getItems().keySet());
                item.add(new ListView<String>("topLinks", subItems) {
                    @Override
                    protected void populateItem(ListItem<String> item) {
                        String subItem = item.getModelObject();
                        item.add(new BookmarkablePageLink("topLink", tab.getItems().get(subItem)).add(new Label("linkLabel", subItem)));
                    }
                });
            }
        });

    }

}
