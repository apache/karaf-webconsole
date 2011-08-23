package org.apache.karaf.webconsole.blueprint.internal.view;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.blueprint.internal.details.DetailsPage;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.framework.ServiceReference;

public class BlueprintActionsPanel extends ActionsPanel<ServiceReference> {

    public BlueprintActionsPanel(String componentId, IModel<ServiceReference> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(ServiceReference object, String id) {
        PageParameters params = new PageParameters();
        params.put("bundleId", object.getBundle().getBundleId());

        Link link = new BookmarkablePageLink(id, DetailsPage.class, params);
        link.add(new Label("label", "View components"));

        return Arrays.asList(link);
    }
}
