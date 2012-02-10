package org.apache.karaf.webconsole.osgi.core.pkg;

import org.apache.felix.utils.manifest.Clause;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.osgi.framework.Bundle;

public class HeaderTable extends DefaultDataTable<Clause> {

	private static final long serialVersionUID = 1L;

	public HeaderTable(String id, IColumn<Clause>[] columns, Bundle bundle, String header) {
		super(id, columns, new HeaderDataProvider(bundle, header), Integer.MAX_VALUE);
	}

}
