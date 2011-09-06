package org.apache.karaf.webconsole.camel.internal.widget;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.context.CamelContextsPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class CamelWidget extends Panel {

    public CamelWidget(String id, List<CamelContext> contexts) {
        super(id);

//        List<CamelContext> subList = new ArrayList<CamelContext>(contexts);
//        if (subList.size() > 4) {
//            subList = subList.subList(0, 4);
//        }
        add(new Label("count", "" + contexts.size()));

        add(new ListView<CamelContext>("contexts"/*, subList*/) {
            @Override
            protected void populateItem(ListItem<CamelContext> item) {
                CamelContext model = item.getModelObject();
                add(new Label("name", model.getName()));
            }
        });

        add(new BookmarkablePageLink<CamelContextsPage>("management", CamelContextsPage.class));
    }

}
