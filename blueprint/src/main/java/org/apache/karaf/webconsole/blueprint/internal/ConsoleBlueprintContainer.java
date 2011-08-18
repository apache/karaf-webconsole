package org.apache.karaf.webconsole.blueprint.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.osgi.service.blueprint.container.BlueprintContainer;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

public class ConsoleBlueprintContainer implements BlueprintContainer, Serializable {

    private BlueprintContainer container;
    private final String symbolicName;
    private final String version;

    public ConsoleBlueprintContainer(BlueprintContainer container, String symbolicName, String version) {
        this.container = container;
        this.symbolicName = symbolicName;
        this.version = version;
        
    }

    public Set getComponentIds() {
        return container.getComponentIds();
    }

    public Object getComponentInstance(String id) {
        return container.getComponentInstance(id);
    }

    public ComponentMetadata getComponentMetadata(String id) {
        return container.getComponentMetadata(id);
    }

    public Collection getMetadata(Class type) {
        return container.getMetadata(type);
    }

    public String getSymbolicName() {
        return symbolicName;
    }

    public String getVersion() {
        return version;
    }

}
