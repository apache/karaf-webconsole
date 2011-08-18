package org.apache.karaf.webconsole.osgi.internal.bundle.decorator;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.bundle.ItemClassModifier;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.startlevel.StartLevel;

class SystemBundleClassModifier implements ItemClassModifier {

    public List<String> getCssClasses(Bundle bundle) {
        BundleContext context = bundle.getBundleContext();
        List<String> classes = new ArrayList<String>();

        if (bundle.getBundleId() == 0) {
            classes.add("framework");
        }

        ServiceReference startLevelReference = context.getServiceReference(StartLevel.class.getName());

        if (startLevelReference != null) {
            StartLevel startLevel = (StartLevel) context.getService(startLevelReference);

            int bundleStartLevel = startLevel.getBundleStartLevel(bundle);
            if (bundleStartLevel < startLevel.getInitialBundleStartLevel()) {
                classes.add("system");
            }
            context.ungetService(startLevelReference);
        }

        return classes;
    }
}