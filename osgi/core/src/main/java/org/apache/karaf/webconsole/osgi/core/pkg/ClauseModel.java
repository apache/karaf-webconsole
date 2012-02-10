package org.apache.karaf.webconsole.osgi.core.pkg;

import org.apache.felix.utils.manifest.Clause;
import org.apache.wicket.model.LoadableDetachableModel;

public class ClauseModel extends LoadableDetachableModel<Clause> {

    private static final long serialVersionUID = 1L;

    public ClauseModel(Clause object) {
        super(object);
    }

    @Override
    protected Clause load() {
        return null;
    }

}
