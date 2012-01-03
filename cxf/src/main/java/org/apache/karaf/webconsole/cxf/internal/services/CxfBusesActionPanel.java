package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class CxfBusesActionPanel extends ActionsPanel<Bus> {

    public CxfBusesActionPanel(String componentId, IModel<Bus> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Bus object, String linkId, String labelId) {
        List<Link> links = new ArrayList<Link>();

        PageParameters params = new PageParameters();
        params.put("busId", object.getId());

        Link link = new BookmarkablePageLink<DetailsPage>(linkId, DetailsPage.class, params);
        link.add(new Label(labelId, "Details"));
        links.add(link);

        return links;
    }
}
