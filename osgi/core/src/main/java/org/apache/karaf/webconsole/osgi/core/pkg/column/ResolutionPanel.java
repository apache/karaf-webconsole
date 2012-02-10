package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Constants;

public class ResolutionPanel extends Panel {

	public ResolutionPanel(String id, IModel<Clause> model) {
		super(id);

		Clause clause = model.getObject();

		String resolution = clause.getDirective(Constants.RESOLUTION_DIRECTIVE);
		if (resolution == null) {
			resolution = Constants.RESOLUTION_MANDATORY;
		}

		String css = "icon-question-sign"; // unknown resolution
		if (Constants.RESOLUTION_MANDATORY.equals(resolution)) {
			css = "icon-lock";
		} else if (Constants.RESOLUTION_OPTIONAL.equals(resolution)) {
			css = "icon-adjust";
		}

		add(new Label("resolution", resolution).setRenderBodyOnly(true));
		Label icon = new Label("icon");
		icon.add(new SimpleAttributeModifier("class", css));
		add(icon);

		add(new SimpleAttributeModifier("title", "Resolution is " + resolution));
	}

}
