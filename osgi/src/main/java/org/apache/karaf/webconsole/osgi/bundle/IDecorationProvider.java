package org.apache.karaf.webconsole.osgi.bundle;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * Decoration provider is responsible for adding new images in bundles list view.
 */
public interface IDecorationProvider {

    /**
     * Creates new panel.
     * 
     * @param componentId Identifier of panel.
     * @param model Model
     * @return Panel.
     */
    Panel getDecoration(String componentId, IModel<Bundle> model);

}
