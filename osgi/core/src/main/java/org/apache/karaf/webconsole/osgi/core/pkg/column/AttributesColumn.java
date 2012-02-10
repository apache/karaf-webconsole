package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;


public class AttributesColumn extends PropertyColumnExt<Clause> {

	public AttributesColumn(String property) {
		super(property);
	}

	public void populateItem(Item<ICellPopulator<Clause>> cellItem, String componentId, IModel<Clause> rowModel) {
		cellItem.add(new AttributesPanel(componentId, rowModel));
	}

}
