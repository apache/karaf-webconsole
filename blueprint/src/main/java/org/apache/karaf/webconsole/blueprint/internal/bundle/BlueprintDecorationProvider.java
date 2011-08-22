package org.apache.karaf.webconsole.blueprint.internal.bundle;

import org.apache.karaf.webconsole.blueprint.internal.IBlueprintBundleStateTracker;
import org.apache.karaf.webconsole.core.panel.StaticImagePanel;
import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * Implementation of decoration provider which decorates bundles list view by
 * adding blueprint image.
 */
public class BlueprintDecorationProvider implements IDecorationProvider {

    private IBlueprintBundleStateTracker tracker;

    public BlueprintDecorationProvider(IBlueprintBundleStateTracker tracker) {
        this.tracker = tracker;
    }

    public Panel getDecoration(final String componentId, IModel<Bundle> model) {
        if (tracker.getState(model.getObject()) != null) {
            return new StaticImagePanel(componentId, new ResourceReference(getClass(), "blueprint.gif"));
        }
        return null;
    }

}
