package org.apache.karaf.webconsole.karaf.admin.create;

import static org.apache.wicket.model.Model.of;

import org.apache.karaf.webconsole.karaf.admin.AdminPage;
import org.apache.karaf.webconsole.karaf.admin.model.InstanceSettingsExt;
import org.apache.karaf.webconsole.karaf.admin.model.KarafInstance;
import org.apache.karaf.webconsole.karaf.admin.settings.InstanceSettingsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/karaf/admin/create")
public class CreateInstancePage extends AdminPage {

    public CreateInstancePage() {
        KarafInstance instance = new KarafInstance();
        instance.setSettings(new InstanceSettingsExt());

        CompoundPropertyModel<KarafInstance> model = new CompoundPropertyModel<KarafInstance>(instance);

        Form<KarafInstance> form = new Form<KarafInstance>("instance", model);
        form.add(new TextField<String>("name"));
        form.add(new InstanceSettingsPanel("settings", of(new InstanceSettingsExt())));

        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                try {
                    KarafInstance instance = (KarafInstance) getForm().getModelObject();
                    admin.createInstance(instance.getName(), instance.getSettings());
                } catch (Exception e) {
                    e.printStackTrace();

                    Session.get().error("Cannot create instance");
                }
            }
        });

        add(form);
    }

}
