package org.apache.karaf.webconsole.servicemix.internal;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.page.SidebarPage;
import org.apache.servicemix.nmr.api.NMR;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class ServiceMixPage extends SidebarPage {

    @PaxWicketBean(name = "nmr")
    protected NMR nmr;

    @PaxWicketBean(name = "smxSidebar")
    private SidebarProvider provider;

    public ServiceMixPage() {
        setSidebarProvider(provider);
    }
}
