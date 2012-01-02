package org.apache.karaf.webconsole.core.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.behavior.AbstractHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;

public abstract class CompositeHeaderContributor extends AbstractHeaderContributor {

    private IHeaderContributor[] contributors;

    protected CompositeHeaderContributor(IHeaderContributor ...contributors) {
        this.contributors = contributors;
    }

    protected abstract IHeaderContributor[] getOwnHeaderContributors();

    @Override
    public final IHeaderContributor[] getHeaderContributors() {
        List<IHeaderContributor> merge = new ArrayList<IHeaderContributor>();
        Collections.addAll(merge, contributors);
        Collections.addAll(merge, getOwnHeaderContributors());
        return merge.toArray(new IHeaderContributor[merge.size()]);
    }

}
