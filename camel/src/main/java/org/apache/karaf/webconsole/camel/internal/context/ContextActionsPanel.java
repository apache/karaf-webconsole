package org.apache.karaf.webconsole.camel.internal.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class ContextActionsPanel extends ActionsPanel<CamelContext> {

    @PaxWicketBean(name = "tracer")
    private TraceContainer container;

    public ContextActionsPanel(String componentId, IModel<CamelContext> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(CamelContext object, String id) {
        List<Link> links = new ArrayList<Link>();

        if (!object.isTracing()) {
            Link link = new Link<CamelContext>(id) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    context.setTracing(true);
                    setResponsePage(CamelContextsPage.class);
                    Session.get().info("Tracing enabled for context " + context.getName());
                }
            };
            link.add(new Label("label", "Enable tracing"));
            links.add(link);
        } else {
            Link link = new Link<CamelContext>(id) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    context.setTracing(false);
                    setResponsePage(CamelContextsPage.class);
                    Session.get().info("Tracing disabled for context " + context.getName());
                }
            };
            link.add(new Label("label", "Disable tracing"));
            links.add(link);
        }

        if (container.isTracePossible(object)) {
            Link link = new Link<CamelContext>(id) {
                @Override
                public void onClick() {
                    CamelContext context = (CamelContext) ContextActionsPanel.this.getDefaultModelObject();
                    setResponsePage(new DumpPage(container, context));
                }
            };
            link.add(new Label("label", "View messages"));
            links.add(link);
        }

        return links;
    }

}
