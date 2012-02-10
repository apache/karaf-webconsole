package org.apache.karaf.webconsole.karaf.feature;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class KarafFeaturesPage extends SinglePage {

    @PaxWicketBean(name = "featuresService")
    protected FeaturesService featuresService;

    //@PaxWicketBean(name = "featuresSidebar")
    //protected SidebarProvider featuresSidebar;

    public KarafFeaturesPage() {
        //setSidebarProvider(featuresSidebar);
    }

}
