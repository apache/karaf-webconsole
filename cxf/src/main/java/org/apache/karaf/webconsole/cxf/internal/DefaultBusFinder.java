package org.apache.karaf.webconsole.cxf.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.cxf.Bus;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.springframework.context.ApplicationContext;

public class DefaultBusFinder implements BusFinder, ServiceListener {

    private BundleContext context;
    private List<Bus> buses = Collections.synchronizedList(new LinkedList<Bus>());

    public DefaultBusFinder(BundleContext context) throws InvalidSyntaxException {
        this.context = context;

        ServiceReference[] references = context.getServiceReferences(ApplicationContext.class.getName(), null);
        for (ServiceReference reference : references) {
            String[] classes = (String[]) reference.getProperty(Constants.OBJECTCLASS);
            for (String clazz : classes) {
                if (ApplicationContext.class.getName().equals(clazz)) {
                    buses.addAll(findSpringBus(reference));
                }
            }
        }
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void serviceChanged(ServiceEvent event) {
        ServiceReference reference = event.getServiceReference();
        String[] classes = (String[]) reference.getProperty(Constants.OBJECTCLASS);

        if (event.getType() == ServiceEvent.REGISTERED) {
            for (String clazz : classes) {
                if (ApplicationContext.class.getName().equals(clazz)) {
                    buses.addAll(findSpringBus(reference));
                }
            }
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            for (String clazz : classes) {
                if (ApplicationContext.class.getName().equals(clazz)) {
                    buses.removeAll(findSpringBus(reference));
                }
            }
            
        }
    }

    private Collection<Bus> findSpringBus(ServiceReference reference) {
        ApplicationContext appContext = (ApplicationContext) context.getService(reference);

        Map<String, Bus> contextBuses = appContext.getBeansOfType(Bus.class);

        context.ungetService(reference);
        return contextBuses.values();
    }

}
