package org.apache.karaf.webconsole.blueprint.internal.view;

import org.apache.wicket.markup.html.basic.Label;

public class DefaultServiceReferencePropertyColumn extends ServiceReferencePropertyColumn {

    public DefaultServiceReferencePropertyColumn(String displayModel, String sortProperty) {
        super(displayModel, sortProperty);
    }

    public DefaultServiceReferencePropertyColumn(String displayModel) {
        super(displayModel);
    }

    protected Label createComponent(String componentId, Object property) {
        return new Label(componentId, property != null ? property.toString() : "null");
    }
}
