package org.apache.karaf.webconsole.blueprint.internal.view;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.ServiceReference;

public class BlueprintDataTable extends DefaultDataTable<ServiceReference> {

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final List COLUMNS = Arrays.asList(
        new DefaultServiceReferencePropertyColumn("Id", "service.id"),
        new DefaultServiceReferencePropertyColumn("Container", "osgi.blueprint.container.symbolicname"),
        new DefaultServiceReferencePropertyColumn("Version", "osgi.blueprint.container.version"),
        new AbstractColumn<ServiceReference>(Model.of("Operations")) {
            public void populateItem(Item<ICellPopulator<ServiceReference>> cellItem, String componentId, IModel<ServiceReference> rowModel) {
                cellItem.add(new BlueprintActionsPanel(componentId, rowModel));
            }
        }
    );

    public BlueprintDataTable(String id, ISortableDataProvider<ServiceReference> dataProvider, int rowsPerPage) {
        super(id, COLUMNS, dataProvider, rowsPerPage);
    }

}
