package org.apache.karaf.webconsole.blueprint.internal.view;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.ServiceReference;

public abstract class ServiceReferencePropertyColumn extends AbstractColumn<ServiceReference> {

    public ServiceReferencePropertyColumn(String displayModel, String sortProperty) {
        super(Model.of(displayModel), sortProperty);
    }

    public ServiceReferencePropertyColumn(String displayModel) {
        super(Model.of(displayModel));
    }

    public final void populateItem(Item<ICellPopulator<ServiceReference>> cellItem, String componentId, IModel<ServiceReference> rowModel) {
        cellItem.add(createComponent(componentId, rowModel.getObject().getProperty(getSortProperty())));
    }

    protected abstract Component createComponent(String componentId, Object property);
}
