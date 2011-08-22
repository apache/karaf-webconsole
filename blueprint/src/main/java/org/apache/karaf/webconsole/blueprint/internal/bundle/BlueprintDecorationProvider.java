package org.apache.karaf.webconsole.blueprint.internal.bundle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.karaf.webconsole.core.panel.StaticImagePanel;
import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.service.blueprint.container.BlueprintEvent;
import org.osgi.service.blueprint.container.BlueprintListener;

public class BlueprintDecorationProvider implements IDecorationProvider, BlueprintListener {

    private Map<Long, BlueprintState> states = new ConcurrentHashMap<Long, BlueprintState>();

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

    public Panel getDecoration(final String componentId, IModel<Bundle> model) {
        if (states.containsKey(model.getObject().getBundleId())) {
            return new StaticImagePanel(componentId, new ResourceReference(getClass(), "blueprint.gif"));
        }
        return null;
    }
}
