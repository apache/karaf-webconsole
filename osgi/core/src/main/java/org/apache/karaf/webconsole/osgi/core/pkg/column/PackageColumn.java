package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class PackageColumn extends PropertyColumnExt<Clause> {

	public PackageColumn(String property) {
		super(property, "name");
	}

	@Override
	public void populateItem(Item<ICellPopulator<Clause>> item, String componentId, IModel<Clause> rowModel) {
		item.add(new PackagePanel(componentId, rowModel));
	}
}
