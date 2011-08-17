package org.apache.karaf.webconsole.core.internal;

import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.DashboardWidget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class DashboardPage extends BasePage {

    @PaxWicketBean(name = "widgets")
    private List<DashboardWidget> widgets;

    public DashboardPage() {
        add(CSSPackageResource.getHeaderContribution(DashboardPage.class, "dashboard.css"));

        add(new ListView<DashboardWidget>("widgets", new ListModel<DashboardWidget>(widgets)) {
            @Override
            protected void populateItem(ListItem<DashboardWidget> item) {
                item.add(item.getModelObject().getWidgetPanel("widget"));
            }
        });

    }

}
