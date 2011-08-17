package org.apache.karaf.webconsole.core.internal;

import java.util.Hashtable;

import org.apache.karaf.webconsole.core.ApplicationReference;
import org.apache.wicket.Application;
import org.apache.wicket.IDestroyer;
import org.apache.wicket.IInitializer;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

public class Initializer implements IInitializer, IDestroyer {

    private BundleContext context;
    private ServiceRegistration registration;

    public Initializer() {
        context = FrameworkUtil.getBundle(getClass()).getBundleContext();
    }

    public void init(final Application application) {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("applicationKey", application.getApplicationKey());

//        // access session from ThreadLocal
//        final Session session = Session.get();
        ApplicationReference reference = new ApplicationReference() {
            public Application getApplication() {
                return application;
            }

            public Session getSession() {
                return Session.get();
            }

            public RequestCycle getRequestCycle() {
                return RequestCycle.get();
            }
        };
        registration = context.registerService(ApplicationReference.class.getName(), reference, properties);
    }

    public void destroy(Application application) {
        if (registration != null) {
            registration.unregister();
        }
    }

}
