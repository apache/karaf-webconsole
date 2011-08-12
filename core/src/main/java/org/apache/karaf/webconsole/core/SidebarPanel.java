package org.apache.karaf.webconsole.core;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.util.ListModel;

public class SidebarPanel<T extends Page> extends Panel {

    public SidebarPanel(String id, Class<T> basePage, List<Class<Page>> subPages) {
        super(id);

        add(new BookmarkablePageLink<T>("masterPageLink", basePage).add(new Label("masterPageLabel", basePage.getName())));

        add(new ListView<Class<Page>>("subPageLinks", new ListModel<Class<Page>>(subPages)) {
            @Override
            protected void populateItem(ListItem<Class<Page>> item) {
                BookmarkablePageLink<T> link = new BookmarkablePageLink<T>("subPageLink", item.getModelObject());
                link.add(new Label("subPageLabel", item.getModelObject().getName()));
                item.add(link);
            }
        });
    }

}
