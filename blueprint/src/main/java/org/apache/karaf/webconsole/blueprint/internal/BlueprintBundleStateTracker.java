package org.apache.karaf.webconsole.blueprint.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.service.blueprint.container.BlueprintEvent;
import org.osgi.service.blueprint.container.BlueprintListener;

public class BlueprintBundleStateTracker implements IBlueprintBundleStateTracker,
    BundleListener, BlueprintListener {

    private Map<Long, BlueprintState> states = new ConcurrentHashMap<Long, BlueprintState>();

    public BlueprintState getState(Bundle bundle) {
        return states.get(bundle.getBundleId());
    }

    public void blueprintEvent(BlueprintEvent event) {
        states.put(event.getBundle().getBundleId(), getState(event));
    }

    public void bundleChanged(BundleEvent event) {
        if (event.getType() == BundleEvent.UNINSTALLED) {
            states.remove(event.getBundle().getBundleId());
        }
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
