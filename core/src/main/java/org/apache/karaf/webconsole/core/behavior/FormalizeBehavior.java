package org.apache.karaf.webconsole.core.behavior;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.JavascriptPackageResource;

public class FormalizeBehavior extends CompositeHeaderContributor {

    public FormalizeBehavior() {
        super(new JQueryBehavior());
    }

    @Override
    protected IHeaderContributor[] getOwnHeaderContributors() {
        return new IHeaderContributor[] {
            CSSPackageResource.getHeaderContribution(FormalizeBehavior.class, "formalize/formalize.css"),
            // formalize don't have releases, so we need to use git hash
            JavascriptPackageResource.getHeaderContribution(FormalizeBehavior.class, "formalize/jquery.formalize-b9528e8.min.js"),
        };
    }

}
