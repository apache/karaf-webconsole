package org.apache.karaf.webconsole.camel.internal.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.CamelPage;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/camel/contexts")
public class CamelContextsPage extends CamelPage {

    @PaxWicketBean(name = "contexts")
    private List<CamelContext> contexts;

    @PaxWicketBean(name = "tracer")
    private TraceContainer container;

    public CamelContextsPage() {
        @SuppressWarnings("unchecked")
        IColumn<CamelContext>[] columns = new IColumn[] {
            new OrdinalColumn<CamelContext>(),
            new PropertyColumnExt<CamelContext>("Name", "name"),
            new PropertyColumnExt<CamelContext>("Version", "version"),
            new PropertyColumnExt<CamelContext>("Status", "status"),
            new PropertyColumnExt<CamelContext>("Uptime", "uptime"),
            new AbstractColumn<CamelContext>(Model.of("Message preview")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new Label(componentId, "" + container.isTracePossible(rowModel.getObject())));
                }
            },
            new AbstractColumn<CamelContext>(Model.of("Tracing enabled")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new Label(componentId, "" + container.isTraced(rowModel.getObject())));
                }
            },
            new AbstractColumn<CamelContext>(Model.of("Operations")) {
                public void populateItem(Item<ICellPopulator<CamelContext>> cellItem, String componentId, IModel<CamelContext> rowModel) {
                    cellItem.add(new ContextActionsPanel(componentId, rowModel));
                }
            }
        };

        ISortableDataProvider<CamelContext> provider = new SortableDataProvider<CamelContext>() {
            public Iterator<? extends CamelContext> iterator(int first, int count) {
                return new ArrayList<CamelContext>(contexts).subList(first, first + count).iterator();
            }

            public int size() {
                return contexts.size();
            }

            public IModel<CamelContext> model(CamelContext object) {
                return new CamelContextModel(contexts, object);
            }
        };

        add(new DefaultDataTable<CamelContext>("contexts", columns, provider, 20));
    }

}
