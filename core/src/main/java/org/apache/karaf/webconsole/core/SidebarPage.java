package org.apache.karaf.webconsole.core;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.internal.SidebarPanel;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.util.ListModel;

public class SidebarPage extends BasePage {

    private Panel sidebar;

    public SidebarPage() {
        ListModel<Class<? extends Page>> listModel = new ListModel<Class<? extends Page>>(getSubPages());

        sidebar = new SidebarPanel("sidebar", getClass(), listModel);
        add(sidebar);
    }

    public Panel getSidebar() {
        return sidebar;
    }

    protected List<Class<? extends Page>> getSubPages() {
        return Collections.emptyList();
    }

}
