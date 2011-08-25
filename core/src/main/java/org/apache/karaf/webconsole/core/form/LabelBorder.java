package org.apache.karaf.webconsole.core.form;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

public class LabelBorder extends Border {

    public LabelBorder(String id, IModel<?> model) {
        super(id, model);
    }

    public LabelBorder(String id) {
        super(id);
    }

}
