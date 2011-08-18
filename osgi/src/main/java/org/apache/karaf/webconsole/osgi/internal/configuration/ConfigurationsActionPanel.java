package org.apache.karaf.webconsole.osgi.internal.configuration;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.service.cm.Configuration;

@SuppressWarnings("rawtypes")
class ConfigurationsActionPanel extends ActionsPanel<Configuration> {

    public ConfigurationsActionPanel(String componentId, IModel<Configuration> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(Configuration object, String id) {
        PageParameters params = new PageParameters();
        params.put("pid", object.getPid());

        Link removeLink = new Link(id) {
            @Override
            public void onClick() {
            }
        };
        removeLink.add(new SimpleAttributeModifier("class", "remove"));
        removeLink.add(new Label("label", "remove"));

        Link editLink = new BookmarkablePageLink<ConfigurationEditPage>(id, ConfigurationEditPage.class, params);
        editLink.add(new Label("label", "edit"));

        return Arrays.asList(editLink, removeLink);
    }
}