package org.apache.karaf.webconsole.osgi.internal;

import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.SidebarPage;
import org.apache.karaf.webconsole.osgi.internal.configuration.ConfigurationsPage;
import org.apache.karaf.webconsole.osgi.internal.event.EventsPage;
import org.apache.wicket.Page;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.BundleContext;

public abstract class OsgiPage extends SidebarPage {

    @PaxWicketBean(name = "blueprintBundleContext")
    protected BundleContext context;

    @Override
    protected List<Class<? extends Page>> getSubPages() {
        List<Class<? extends Page>> subpages = new LinkedList<Class<? extends Page>>();
        subpages.add(ConfigurationsPage.class);
        subpages.add(EventsPage.class);
        return subpages;
    }

}
