package org.apache.karaf.webconsole.blueprint.internal;

import org.osgi.framework.Bundle;

public interface IBlueprintBundleStateTracker {

    BlueprintState getState(Bundle bundle);

}
