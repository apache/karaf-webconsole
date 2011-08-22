package org.apache.karaf.webconsole.blueprint.internal.bundle;

public enum BlueprintState {
    Unknown,
    Creating,
    Created,
    Destroying,
    Destroyed,
    Failure,
    GracePeriod,
    Waiting
}