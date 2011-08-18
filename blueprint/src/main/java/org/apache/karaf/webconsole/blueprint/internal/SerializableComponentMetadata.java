package org.apache.karaf.webconsole.blueprint.internal;

import java.io.Serializable;
import java.util.List;

import org.osgi.service.blueprint.reflect.ComponentMetadata;

public class SerializableComponentMetadata implements ComponentMetadata, Serializable {

    private transient ComponentMetadata metadata;

    public SerializableComponentMetadata(ComponentMetadata metadata) {
        this.metadata = metadata;
    }

    public String getId() {
        return metadata.getId();
    }

    public int getActivation() {
        return metadata.getActivation();
    }

    public List<String> getDependsOn() {
        return metadata.getDependsOn();
    }

    public String getTypeName() {
        return metadata.getClass().getName();
    }
}
