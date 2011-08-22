package org.apache.karaf.webconsole.blueprint.internal.bundle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.karaf.webconsole.osgi.bundle.IColumnProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.Bundle;
import org.osgi.service.blueprint.container.BlueprintEvent;
import org.osgi.service.blueprint.container.BlueprintListener;

public class BlueprintColumnProvider implements IColumnProvider, BlueprintListener {

    private Map<Long, BlueprintState> states = new ConcurrentHashMap<Long, BlueprintState>();

    public IColumn<Bundle> getColumn() {
        return new AbstractColumn<Bundle>(Model.of("Blueprint")) {
            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, String componentId, IModel<Bundle> rowModel) {
                Bundle bundle = rowModel.getObject();
                if (states.containsKey(bundle.getBundleId())) {
                    cellItem.add(new Label(componentId, states.get(bundle.getBundleId()).name()));
                } else {
                    cellItem.add(new Label(componentId));
                }
            }
        };
    }

    public void blueprintEvent(BlueprintEvent event) {
        states.put(event.getBundle().getBundleId(), getState(event));
    }

    private BlueprintState getState(BlueprintEvent blueprintEvent) {
        switch (blueprintEvent.getType()) {
            case BlueprintEvent.CREATING:
                return BlueprintState.Creating;
            case BlueprintEvent.CREATED:
                return BlueprintState.Created;
            case BlueprintEvent.DESTROYING:
                return BlueprintState.Destroying;
            case BlueprintEvent.DESTROYED:
                return BlueprintState.Destroyed;
            case BlueprintEvent.FAILURE:
                return BlueprintState.Failure;
            case BlueprintEvent.GRACE_PERIOD:
                return BlueprintState.GracePeriod;
            case BlueprintEvent.WAITING:
                return BlueprintState.Waiting;
            default:
                return BlueprintState.Unknown;
        }
    }
}
