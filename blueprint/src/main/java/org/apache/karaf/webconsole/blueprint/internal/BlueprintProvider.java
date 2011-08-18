package org.apache.karaf.webconsole.blueprint.internal;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;

public class BlueprintProvider extends SortableDataProvider<BlueprintContainer> {

    private final List<ServiceReference> containers;

    public BlueprintProvider(List<ServiceReference> containers) {
        this.containers = containers;
    }

    public Iterator<? extends BlueprintContainer> iterator(int first, int count) {
        containers.subList(first, count).iterator();
        return new Iterator<BlueprintContainer>() {
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return false;
            }

            public BlueprintContainer next() {
                // TODO Auto-generated method stub
                return null;
            }

            public void remove() {
                // TODO Auto-generated method stub
                
            }
        };
    }

    public IModel<BlueprintContainer> model(BlueprintContainer object) {
        return null;
    }

    public int size() {
        return containers.size();
    }

}
