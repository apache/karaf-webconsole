package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.bundle.IActionProvider;
import org.apache.karaf.webconsole.osgi.internal.bundle.DetailsPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public class BundleActionsPanel extends ActionsPanel<Bundle> {

    public BundleActionsPanel(String componentId, final IModel<Bundle> model, List<IActionProvider> actionProviders) {
        super(componentId, model);

        /*
        add(new ListView<IActionProvider>("extensions", new ListModel<IActionProvider>(actionProviders)) {
            @Override
            protected void populateItem(ListItem<IActionProvider> item) {
                item.add(item.getModelObject().create("extension", model.getObject()));
            }
        });
        */
    }

    @Override
    protected List<Link> getLinks(Bundle object, String id) {
        PageParameters params = new PageParameters();
        params.put("bundleId", object.getBundleId());

        List<Link> links = new ArrayList<Link>();

        // details link
        Link link = new BookmarkablePageLink<DetailsPage>(id, DetailsPage.class, params);
        link.add(new Label("label", "Details"));

        links.add(link);

        return links;
    }
}
