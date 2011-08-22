package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import org.apache.karaf.webconsole.core.panel.CssImagePanel;
import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.service.startlevel.StartLevel;

public class SystemBundleDecorationProvider implements IDecorationProvider {

    private StartLevel startLevel;

    public SystemBundleDecorationProvider(StartLevel startLevel) {
        this.startLevel = startLevel;
    }

    public Panel getDecoration(String componentId, IModel<Bundle> model) {
        Bundle bundle = model.getObject();

        int bundleStartLevel = startLevel.getBundleStartLevel(bundle);
        if (bundleStartLevel < startLevel.getInitialBundleStartLevel()) {
            return new CssImagePanel(componentId, "system");
        }

        return null;
    }
}