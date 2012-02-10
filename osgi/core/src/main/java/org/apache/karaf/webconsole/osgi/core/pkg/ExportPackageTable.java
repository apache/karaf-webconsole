package org.apache.karaf.webconsole.osgi.core.pkg;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.osgi.core.pkg.column.AttributesColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.DirectivesColumn;
import org.apache.karaf.webconsole.osgi.core.pkg.column.PackageColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

public class ExportPackageTable extends HeaderTable {

	@SuppressWarnings("unchecked")
	static IColumn<Clause>[] columns = new IColumn[] {
		new OrdinalColumn<Clause>(),
		new PropertyColumnExt<Clause>("Package", "name"),
		new PackageColumn("Export"),
//		new ResolutionColumn("Resolution"),
//		new VersionRangeColumn("Version"),
		new AttributesColumn("Attributes"),
		new DirectivesColumn("Directives"),
	};

	public ExportPackageTable(String id, Bundle bundle) {
		super(id, columns, bundle, Constants.EXPORT_PACKAGE);
	}

}
