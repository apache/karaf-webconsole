package org.apache.karaf.webconsole.osgi.core.pkg;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.AttributesColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.DirectivesColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.PackageColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.ResolutionColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.VersionRangeColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

public class ImportPackageTable extends HeaderTable {

	@SuppressWarnings("unchecked")
	static IColumn<Clause>[] columns = new IColumn[] {
		new OrdinalColumn<Clause>(),
		new PackageColumn("Import"),
		new ResolutionColumn("Resolution"),
		new VersionRangeColumn("Version"),
		new AttributesColumn("Attributes"),
		new DirectivesColumn("Directives"),
	};

	public ImportPackageTable(String id, Bundle bundle) {
		super(id, columns, bundle, Constants.IMPORT_PACKAGE);
	}

}
