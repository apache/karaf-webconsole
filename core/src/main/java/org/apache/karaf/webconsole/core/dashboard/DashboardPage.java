package org.apache.karaf.webconsole.core.dashboard;

import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class DashboardPage extends BasePage {

    @PaxWicketBean(name = "widgets")
    private List<WidgetProvider> widgets;

    public DashboardPage() {
        add(CSSPackageResource.getHeaderContribution(DashboardPage.class, "dashboard.css"));

        add(new Label("noWidgets", "So far there is no widgets to display") {
            @Override
            public boolean isVisible() {
                return widgets.size() == 0;
            }
        });

        add(new ListView<WidgetProvider>("widgets", new ListModel<WidgetProvider>(widgets)) {
            @Override
            protected void populateItem(ListItem<WidgetProvider> item) {
                item.add(item.getModelObject().getWidgetPanel("widget"));
            }
        });

    }

}
