package org.apache.karaf.webconsole.osgi.internal.log;

import java.util.Arrays;

import org.apache.wicket.extensions.markup.html.form.select.IOptionRenderer;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOptions;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class OptionsForm extends Form<Options> {

    public OptionsForm(String id, CompoundPropertyModel<Options> model) {
        super(id, model);

        IModel<Priority> priority = model.bind("priority");
        Select<Priority> select = new Select<Priority>("priority", priority);
        select.add(new SelectOptions<Priority>("options", Arrays.asList(Priority.values()), new IOptionRenderer<Priority>() {
            public String getDisplayValue(Priority object) {
                return object.name();
            }

            public IModel<Priority> getModel(Priority value) {
                return Model.of(value);
            }
        }));

        add(select);
        add(new TextField<Long>("dateFrom", Long.class));
        add(new TextField<Long>("dateTo", Long.class));
        add(new TextField<String>("messageFragment",String.class));
        add(new TextField<String>("bundleNameFragment",String.class));


        add(new SubmitLink("submit"));
    }

}
