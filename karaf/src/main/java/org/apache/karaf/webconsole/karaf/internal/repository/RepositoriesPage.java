package org.apache.karaf.webconsole.karaf.internal.repository;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.karaf.internal.RepositoriesProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/karaf/repositories")
public class RepositoriesPage extends BasePage {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public RepositoriesPage() {
        IColumn[] columns = new IColumn[] {
            new PropertyColumn<Repository>(Model.of("name"), "name", "name"),
            new PropertyColumn<Repository>(Model.of("URI"), "URI", "URI"),
            new PropertyColumn<Repository>(Model.of("valid"), "valid", "valid")
        };

        add(new DefaultDataTable<Repository>("repositories", columns, new RepositoriesProvider(featuresService), 20));
    }


}
