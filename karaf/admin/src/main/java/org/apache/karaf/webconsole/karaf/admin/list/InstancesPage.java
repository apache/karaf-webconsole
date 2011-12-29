package org.apache.karaf.webconsole.karaf.admin.list;

import static org.apache.wicket.model.Model.of;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.karaf.admin.AdminPage;
import org.apache.karaf.webconsole.karaf.admin.model.InstancesDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class InstancesPage extends AdminPage {

    public InstancesPage() {
        List<IColumn<Instance>> columns = new ArrayList<IColumn<Instance>>();
        columns.add(new OrdinalColumn<Instance>());
        columns.add(new PropertyColumnExt<Instance>("PID", "pid"));
        columns.add(new PropertyColumnExt<Instance>("Name", "name"));
        columns.add(new PropertyColumnExt<Instance>("Root", "root"));
        columns.add(new PropertyColumnExt<Instance>("State", "state"));
        columns.add(new PropertyColumnExt<Instance>("Location", "location"));
        columns.add(new AbstractColumn<Instance>(of("Actions")) {
            public void populateItem(Item<ICellPopulator<Instance>> cellItem, String componentId, IModel<Instance> rowModel) {
                cellItem.add(new InstanceActionsPanel(componentId, rowModel));
            }
        });

        add(new DefaultDataTable<Instance>("instances", columns, new InstancesDataProvider(admin), 20));
    }

}
