package org.apache.karaf.webconsole.karaf.admin.settings;

import org.apache.karaf.webconsole.karaf.admin.model.InstanceSettingsExt;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class InstanceSettingsPanel extends Panel {

    public InstanceSettingsPanel(String id, IModel<InstanceSettingsExt> model) {
        super(id, model);

        add(new InstanceSettingsForm("settings", model));
    }

}
