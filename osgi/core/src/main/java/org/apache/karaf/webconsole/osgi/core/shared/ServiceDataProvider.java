package org.apache.karaf.webconsole.osgi.core.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * OSGi service registry data provider. Scans registry for services matching
 * given criteria.
 * <strong>The service list might be outdated before model is used!</strong>
 */
public class ServiceDataProvider extends SortableDataProvider<ServiceReference> {

    private static final long serialVersionUID = 1L;

    private transient List<ServiceReference> services;

    private BundleContext context;

    private final String clazz;

    private final String filter;

    /**
     * Base constructor.
     * 
     * @param context Bundle context.
     * @param clazz Class name.
     * @param filter Filter to apply.
     */
    public ServiceDataProvider(BundleContext context, String clazz, String filter) {
        this.context = context;
        this.clazz = clazz;
        this.filter = filter;
    }

    private List<ServiceReference> getServices() {
        if (services == null) {
            try {
                ServiceReference[] references = context.getServiceReferences(clazz, filter);
                this.services = references != null ? Arrays.asList(references) : new ArrayList<ServiceReference>();
            } catch (InvalidSyntaxException e) {
                throw new RuntimeException("Unable to list services", e);
            }
        }
        return services;
    }

    public ServiceDataProvider(BundleContext context, String clazz) {
        this(context, clazz, "(objectClass=*)");
    }

    public ServiceDataProvider(BundleContext context, Class<?> clazz, String filter) {
        this(context, clazz.getName(), filter);
    }

    public ServiceDataProvider(BundleContext context, Class<?> clazz) {
        this(context, clazz.getName());
    }

    // provide methods
    public Iterator<? extends ServiceReference> iterator(int first, int count) {
        return getServices().subList(first, count).iterator();
    }

    public IModel<ServiceReference> model(ServiceReference object) {
        return new ServiceReferenceModel(object);
    }

    public int size() {
        return getServices().size();
    }

}
