package org.apache.karaf.webconsole.osgi.core.pkg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.osgi.core.manifest.ManifestUtil;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderDataProvider extends SortableDataProvider<Clause> {

    private static final long serialVersionUID = 1L;
    private transient Logger logger = LoggerFactory.getLogger(getClass());
    private transient Clause[] clauses;

    public HeaderDataProvider(Bundle bundle, String header) {
        try {
            clauses = ManifestUtil.getHeader(bundle, header);
        } catch (IOException e) {
            clauses = new Clause[0];
            logger.error("Cannot parse bundle headers", e);
        }
    }

    public Iterator<? extends Clause> iterator(int first, int count) {
        List<Clause> clauses = new ArrayList<Clause>();
        clauses.addAll(Arrays.asList(this.clauses));
        return clauses.subList(first, count).iterator();
    }

    public int size() {
        return clauses.length;
    }

    public IModel<Clause> model(Clause object) {
        return new ClauseModel(object);
    }

}
