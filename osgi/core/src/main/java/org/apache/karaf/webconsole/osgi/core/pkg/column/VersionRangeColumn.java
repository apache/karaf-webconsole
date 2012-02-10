package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Constants;

public class VersionRangeColumn extends PropertyColumnExt<Clause> {

	public VersionRangeColumn(String property) {
		super(property);
	}

	@Override
	public void populateItem(Item<ICellPopulator<Clause>> item, String componentId, IModel<Clause> rowModel) {
		String version = rowModel.getObject().getAttribute(Constants.VERSION_ATTRIBUTE);
		item.add(new Label(componentId, version));
	}
}
