package org.apache.karaf.webconsole.karaf.admin.list;

import static org.apache.wicket.model.Model.of;

import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.AdvancedDataTable;
import org.apache.karaf.webconsole.core.table.advanced.ColumnList;
import org.apache.karaf.webconsole.karaf.admin.AdminPage;
import org.apache.karaf.webconsole.karaf.admin.model.InstancesDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.service.prefs.PreferencesService;

public class InstancesPage extends AdminPage {

	@PaxWicketBean(name = "preferencesService")
	private PreferencesService preferences;

    public InstancesPage() {
        ColumnList<Instance> columns = new ColumnList<Instance>(preferences.getUserPreferences("karaf"));

        columns.addColumn(new OrdinalColumn<Instance>());
        columns.addColumn(new PropertyColumnExt<Instance>("PID", "pid"));
        columns.addColumn(new PropertyColumnExt<Instance>("Name", "name"));
        columns.addColumn(new PropertyColumnExt<Instance>("Root", "root"));
        columns.addColumn(new PropertyColumnExt<Instance>("State", "state"));
        columns.addColumn(new PropertyColumnExt<Instance>("Location", "location"));
        columns.addColumn(new AbstractColumn<Instance>(of("Actions")) {
            public void populateItem(Item<ICellPopulator<Instance>> cellItem, String componentId, IModel<Instance> rowModel) {
                cellItem.add(new InstanceActionsPanel(componentId, rowModel));
            }
        });

        add(new AdvancedDataTable<Instance>("instances", columns, new InstancesDataProvider(admin), 20));
    }

}
