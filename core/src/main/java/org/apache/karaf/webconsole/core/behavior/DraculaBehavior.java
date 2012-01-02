package org.apache.karaf.webconsole.core.behavior;

import static org.apache.wicket.markup.html.JavascriptPackageResource.getHeaderContribution;

import org.apache.wicket.markup.html.IHeaderContributor;

public class DraculaBehavior extends CompositeHeaderContributor {

    public DraculaBehavior() {
        super(new JQueryBehavior(), new RaphaelBehavior());
    }

    @Override
    protected IHeaderContributor[] getOwnHeaderContributors() {
        return new IHeaderContributor[] {
            getHeaderContribution(DraculaBehavior.class, "dracula/Curry-1.0.1.js"),
            getHeaderContribution(DraculaBehavior.class, "dracula/seedrandom.js"),
            getHeaderContribution(DraculaBehavior.class, "dracula/dracula_graph.js"),
            getHeaderContribution(DraculaBehavior.class, "dracula/dracula_graffle.js"),
            getHeaderContribution(DraculaBehavior.class, "dracula/dracula_algorithms.js"),
        };
    }
}

