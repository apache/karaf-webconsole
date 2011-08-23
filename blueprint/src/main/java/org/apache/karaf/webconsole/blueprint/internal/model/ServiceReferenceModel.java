package org.apache.karaf.webconsole.blueprint.internal.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

public class ServiceReferenceModel extends LoadableDetachableModel<ServiceReference> {

    private final Bundle bundle;
    private String[] classes;

    public ServiceReferenceModel(ServiceReference object) {
        this(object, object.getBundle());
    }

    public ServiceReferenceModel(ServiceReference object, Bundle bundle) {
        this.bundle = bundle;
        classes = (String[]) object.getProperty("objectClass");
    }

    @Override
    protected ServiceReference load() {
        ServiceReference[] services = bundle.getRegisteredServices();

        for (ServiceReference reference : services) {
            if (classes.equals(reference.getProperty("objectClass"))) {
                return reference;
            }
        }

        throw new MissingServiceReferenceException(bundle.getSymbolicName(), classes);
    }

}
