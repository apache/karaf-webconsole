package org.apache.karaf.webconsole.karaf.features;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.page.SidebarPage;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class KarafFeaturesPage extends SidebarPage {

    @PaxWicketBean(name = "featuresService")
    protected FeaturesService featuresService;

    @PaxWicketBean(name = "featuresSidebar")
    protected SidebarProvider featuresSidebar;

    public KarafFeaturesPage() {
        setSidebarProvider(featuresSidebar);
    }

}
