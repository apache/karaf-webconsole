package org.apache.karaf.webconsole.blueprint.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.webconsole.blueprint.internal.model.ServiceReferenceModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.ServiceReference;

public class BlueprintDataProvider extends SortableDataProvider<ServiceReference> {

    private final List<ServiceReference> containers;

    public BlueprintDataProvider(List<ServiceReference> containers) {
        this.containers = containers;
    }

    public Iterator<ServiceReference> iterator(int first, int count) {
        return new ArrayList<ServiceReference>(containers).subList(first, first+count).iterator();
    }

    public IModel<ServiceReference> model(ServiceReference object) {
        return new ServiceReferenceModel(object);
    }

    public int size() {
        return containers.size();
    }

}
