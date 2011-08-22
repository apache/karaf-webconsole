package org.apache.karaf.webconsole.core.panel;

import static org.apache.wicket.model.Model.of;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Panel which creates an span element with background image.
 */
public class CssImagePanel extends AbstractImagePanel {

    public CssImagePanel(String id, String cssClass) {
        super(id);

        Label label = new Label("decorationLabel");
        label.add(new AttributeAppender("class", of(cssClass), " "));
        add(label);
    }

}
