package org.apache.karaf.webconsole.blueprint.internal;

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