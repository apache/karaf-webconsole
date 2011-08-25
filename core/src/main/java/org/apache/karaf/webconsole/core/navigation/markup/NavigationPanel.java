package org.apache.karaf.webconsole.core.navigation.markup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.Page;
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
                ConsoleTab tab = item.getModelObject();

                item.add(new BookmarkablePageLink("moduleLink", tab.getModuleHomePage()).add(new Label("moduleLabel",
                    tab.getLabel())));

                List<SimplifiedModel> model = new ArrayList<NavigationPanel.SimplifiedModel>();
                for (Entry<String, Class<? extends Page>> entries : tab.getItems().entrySet()) {
                    model.add(new SimplifiedModel(entries.getKey(), entries.getValue()));
                }

                item.add(new ListView<SimplifiedModel>("topLinks", model) {
                    @Override
                    protected void populateItem(ListItem<SimplifiedModel> item) {
                        SimplifiedModel subItem = item.getModelObject();
                        item.add(new BookmarkablePageLink("topLink", subItem.getClazz()).add(new Label("linkLabel",
                            subItem.getName())));
                    }
                });
            }
        });

    }

    private static class SimplifiedModel implements Serializable {

        private String name;
        private Class<? extends Page> clazz;

        public SimplifiedModel(String name, Class<? extends Page> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class<? extends Page> getClazz() {
            return clazz;
        }

        public void setClazz(Class<? extends Page> clazz) {
            this.clazz = clazz;
        }

    }

}
