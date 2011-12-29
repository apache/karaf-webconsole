package org.apache.karaf.webconsole.karaf.admin.settings;

import org.apache.karaf.webconsole.karaf.admin.model.InstanceSettingsExt;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class InstanceSettingsForm extends Form<InstanceSettingsExt> {

    public InstanceSettingsForm(String id, IModel<InstanceSettingsExt> model) {
        super(id, new CompoundPropertyModel<InstanceSettingsExt>(model));

        add(new TextField<Integer>("sshPort"));
        add(new TextField<Integer>("rmiRegistryPort"));
        add(new TextField<Integer>("rmiServerPort"));

        add(new TextArea<String>("javaOpts"));
        add(new TextField<String>("location"));

        // we need additional checks here
        //add(new TextArea<String>("features"));
        //add(new TextArea<String>("featureURLs"));
    }

}
