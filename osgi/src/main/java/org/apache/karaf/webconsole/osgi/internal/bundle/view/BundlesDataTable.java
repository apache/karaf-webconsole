package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.osgi.framework.Bundle;

public class BundlesDataTable extends DefaultDataTable<Bundle> {

    public BundlesDataTable(String id, List<IColumn<Bundle>> columns, ISortableDataProvider<Bundle> dataProvider, int rowsPerPage) {
        super(id, columns, dataProvider, rowsPerPage);
    }

}
