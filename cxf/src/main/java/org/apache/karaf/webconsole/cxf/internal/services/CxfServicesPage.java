package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class CxfServicesPage extends SinglePage {

    @PaxWicketBean(name = "busList")
    private List<Bus> buses;

    public CxfServicesPage() {
        @SuppressWarnings("unchecked")
        IColumn<Bus>[] columns = new IColumn[] {
            new OrdinalColumn<Bus>(),
            new PropertyColumnExt<Bus>("Bus id", "id"),
            new AbstractColumn<Bus>(Model.of("Actions")) {
                public void populateItem(Item<ICellPopulator<Bus>> cellItem, String componentId, IModel<Bus> rowModel) {
                    cellItem.add(new CxfBusesActionPanel(componentId, rowModel));
                }
            }
        };

        ISortableDataProvider<Bus> provider = new SortableDataProvider<Bus>() {
            public Iterator<? extends Bus> iterator(int first, int count) {
                return new ArrayList<Bus>(buses).subList(first, first + count).iterator();
            }

            public int size() {
                return buses.size();
            }

            public IModel<Bus> model(Bus object) {
                return new BusModel(buses, object);
            }
        };

        add(new DefaultDataTable<Bus>("buses", columns, provider, 20));
    }

}
