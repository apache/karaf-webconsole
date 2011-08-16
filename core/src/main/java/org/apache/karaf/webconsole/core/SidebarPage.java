package org.apache.karaf.webconsole.core;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.internal.SidebarPanel;
import org.apache.wicket.Page;
import org.apache.wicket.model.util.ListModel;

public class SidebarPage extends BasePage {

    public SidebarPage() {
        ListModel<Class<? extends Page>> listModel = new ListModel<Class<? extends Page>>(getSubPages());

        add(new SidebarPanel("sidebar", getClass(), listModel));
    }

    protected List<Class<? extends Page>> getSubPages() {
        return Collections.emptyList();
    }

}
