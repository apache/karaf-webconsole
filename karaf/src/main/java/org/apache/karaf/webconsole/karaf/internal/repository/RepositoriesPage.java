package org.apache.karaf.webconsole.karaf.internal.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.SidebarPage;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.karaf.internal.RepositoriesProvider;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/karaf/repositories")
public class RepositoriesPage extends SidebarPage {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public RepositoriesPage() {
        IColumn[] columns = new IColumn[] {
            new OrdinalColumn<Repository>(),
            new PropertyColumn<Repository>(Model.of("name"), "name", "name"),
            new PropertyColumn<Repository>(Model.of("URI"), "URI", "URI"),
            new PropertyColumn<Repository>(Model.of("valid"), "valid", "valid"),
            new AbstractColumn<Repository>(Model.of("Operations")) {
                public void populateItem(Item<ICellPopulator<Repository>> cellItem, String componentId, IModel<Repository> rowModel) {
                    cellItem.add(new RepositoriesActionPanel(componentId, rowModel));
                }
            }
        };

        add(new DefaultDataTable<Repository>("repositories", columns, new RepositoriesProvider(featuresService), 20));
    }

    @Override
    protected List<Class<? extends Page>> getSubPages() {
        List<Class<? extends Page>> list = new ArrayList<Class<? extends Page>>();
        list.add(AddRepositoryPage.class);
        return list;
    }

}
