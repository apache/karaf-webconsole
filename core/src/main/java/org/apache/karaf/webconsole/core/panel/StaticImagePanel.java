package org.apache.karaf.webconsole.core.panel;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;

/**
 * Panel which wraps an image.
 */
public class StaticImagePanel extends AbstractImagePanel {

    public StaticImagePanel(String id, ResourceReference resource) {
        super(id);

        add(new Image("decorationImage", resource));
    }

}
