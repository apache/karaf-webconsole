package org.apache.karaf.webconsole.karaf.internal.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class RepositoriesActionPanel extends ActionsPanel<Repository> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public RepositoriesActionPanel(String componentId, IModel<Repository> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Repository object, String id) {
        List<Link> links = new ArrayList<Link>();

        Link remove = new Link(id) {
            @Override
            public void onClick() {
                Repository object = (Repository) RepositoriesActionPanel.this.getDefaultModelObject();

                featuresService.removeRepository(object.getURI());

                Session.get().info("Repository " + object.getURI() + " was removed");
            }
        };
        remove.add(new Label("label", "Remove"));

        links.add(remove);

        return links;
    }

}
