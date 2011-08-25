package org.apache.karaf.webconsole.core.table;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Column with ordinal number.
 */
public class OrdinalColumn<T> extends AbstractColumn<T> {

    transient int counter = 1;

    public OrdinalColumn(String displayModel, String sortProperty) {
        super(Model.of(displayModel), sortProperty);
    }

    public OrdinalColumn(String displayModel) {
        super(Model.of(displayModel));
    }

    public OrdinalColumn() {
        this("No.", "no");
    }

    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        cellItem.add(new Label(componentId, "" + counter++));
    }

    @Override
    public void detach() {
        super.detach();
        counter = 1;
    }
}
