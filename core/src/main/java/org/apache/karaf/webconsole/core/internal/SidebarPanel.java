package org.apache.karaf.webconsole.core.internal;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.util.ListModel;

public class SidebarPanel extends Panel {

    public SidebarPanel(String id, Class<? extends Page> basePage, ListModel<Class<? extends Page>> listModel) {
        super(id, listModel);

        add(new BookmarkablePageLink<Page>("masterPageLink", basePage).add(new Label("masterPageLabel", basePage.getName())));

        add(new ListView<Class<? extends Page>>("subPageLinks", listModel) {
            @Override
            protected void populateItem(ListItem<Class<? extends Page>> item) {
                BookmarkablePageLink<Page> link = new BookmarkablePageLink<Page>("subPageLink", item.getModelObject());
                link.add(new Label("subPageLabel", item.getModelObject().getName()));
                item.add(link);
            }
        });
    }

}
