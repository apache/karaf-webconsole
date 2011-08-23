package org.apache.karaf.webconsole.blueprint.internal.bundle;

import org.apache.karaf.webconsole.blueprint.internal.IBlueprintBundleStateTracker;
import org.apache.karaf.webconsole.blueprint.internal.details.DetailsPage;
import org.apache.karaf.webconsole.osgi.bundle.IActionProvider;
import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.osgi.framework.Bundle;

public class BlueprintActionProvider implements IActionProvider {

    private IBlueprintBundleStateTracker tracker;

    public BlueprintActionProvider(IBlueprintBundleStateTracker tracker) {
        this.tracker = tracker;
    }

    public Component create(String componentId, Bundle model) {
        PageParameters params = new PageParameters();
        params.put("bundleId", model.getBundleId());
        BookmarkablePageLink link = new BookmarkablePageLink(componentId, DetailsPage.class, params);
        link.setModel(Model.of("Blueprint"));
        return link;
    }

}
