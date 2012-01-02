package org.apache.karaf.webconsole.core.behavior;

import org.apache.wicket.behavior.AbstractHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.JavascriptPackageResource;

public class RaphaelBehavior extends AbstractHeaderContributor {

    @Override
    public IHeaderContributor[] getHeaderContributors() {
        return new IHeaderContributor[] {
            JavascriptPackageResource.getHeaderContribution(RaphaelBehavior.class, "raphael/raphael-1.3.1.min.js")
        };
    }

}
