package org.apache.karaf.webconsole.osgi.core.pkg.column;

import java.util.Arrays;

import org.apache.felix.utils.manifest.Attribute;
import org.apache.felix.utils.manifest.Clause;
import org.apache.felix.utils.manifest.Directive;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class AttributesPanel extends Panel {

	public AttributesPanel(String componentId, IModel<Clause> model) {
		super(componentId, new CompoundPropertyModel<Clause>(model));

		add(new ListView<Attribute>("attributes", Arrays.asList(model.getObject().getAttributes())) {
			@Override
			protected void populateItem(ListItem<Attribute> item) {
				item.setModel(new CompoundPropertyModel<Attribute>(item.getModel()));
				item.add(new Label("name"));
				item.add(new Label("value"));
			}
		});
	}

}
