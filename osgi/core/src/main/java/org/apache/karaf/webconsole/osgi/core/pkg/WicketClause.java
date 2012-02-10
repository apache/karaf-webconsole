package org.apache.karaf.webconsole.osgi.core.pkg;

import java.io.Serializable;

import org.apache.felix.utils.manifest.Clause;

public class WicketClause extends Clause implements Serializable {

	private static final long serialVersionUID = 1428726206399185187L;

	public WicketClause(Clause clause) {
		super(clause.getName(), clause.getDirectives(), clause.getAttributes());
	}

}
