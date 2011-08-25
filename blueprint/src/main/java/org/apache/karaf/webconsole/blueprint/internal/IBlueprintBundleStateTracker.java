package org.apache.karaf.webconsole.blueprint.internal;

import java.io.Serializable;

import org.osgi.framework.Bundle;

public interface IBlueprintBundleStateTracker extends Serializable {

    BlueprintState getState(Bundle bundle);

}
