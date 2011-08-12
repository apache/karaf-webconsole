package org.apache.karaf.webconsole.core;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BasePage extends WebPage {

    @PaxWicketBean(name = "tabs")
    private transient List<ConsoleTab> tabs;

    public BasePage() {
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "style.css"));

        add(new Label("footer", "Apache Karaf Console"));

        add(new ListView<ConsoleTab>("tabs", tabs) {
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

        List tabPanels = new ArrayList();
        tabPanels.add(new AbstractTab(new Model("first tab")) {
            public Panel getPanel(String panelId) {
                return new TabPanel1(panelId);
            }
        });

        add(new TabbedPanel("tabPanels", tabPanels));


    }

    class TabPanel1 extends Panel {
        public TabPanel1(String id) {
            super(id);
        }
    }
}
