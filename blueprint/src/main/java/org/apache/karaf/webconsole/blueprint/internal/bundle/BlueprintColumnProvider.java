package org.apache.karaf.webconsole.blueprint.internal.bundle;

import org.apache.karaf.webconsole.blueprint.internal.BlueprintState;
import org.apache.karaf.webconsole.blueprint.internal.IBlueprintBundleStateTracker;
import org.apache.karaf.webconsole.osgi.bundle.IColumnProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.Bundle;

/**
 * Provides blueprint column in bundles view.
 */
public class BlueprintColumnProvider implements IColumnProvider {

    private IBlueprintBundleStateTracker tracker;

    public BlueprintColumnProvider(IBlueprintBundleStateTracker tracker) {
        this.tracker = tracker;
    }

    public IColumn<Bundle> getColumn() {
        return new AbstractColumn<Bundle>(Model.of("Blueprint")) {
            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, String componentId, IModel<Bundle> rowModel) {
                Bundle bundle = rowModel.getObject();
                BlueprintState state = tracker.getState(bundle);
                if (state != null) {
                    cellItem.add(new Label(componentId, state.name()));
                } else {
                    cellItem.add(new Label(componentId));
                }
            }
        };
    }

}
