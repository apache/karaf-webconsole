package org.apache.karaf.webconsole.osgi.core.pkg.column;

import java.util.Arrays;

import org.apache.felix.utils.manifest.Clause;
import org.apache.felix.utils.manifest.Directive;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class DirectivesPanel extends Panel {

	public DirectivesPanel(String componentId, IModel<Clause> model) {
		super(componentId);

		add(new ListView<Directive>("directives", Arrays.asList(model.getObject().getDirectives())) {
			@Override
			protected void populateItem(ListItem<Directive> item) {
				item.setModel(new CompoundPropertyModel<Directive>(item.getModel()));
				item.add(new Label("name"));
				item.add(new Label("value"));
			}
		});
	}

}