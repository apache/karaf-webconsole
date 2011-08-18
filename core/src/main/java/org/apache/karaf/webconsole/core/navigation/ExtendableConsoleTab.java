package org.apache.karaf.webconsole.core.navigation;

import java.util.Collection;
import java.util.Map;

import org.apache.wicket.Page;

/**
 * Implementation of console tab which allows external providers to put own
 * items to it.
 */
public class ExtendableConsoleTab implements ConsoleTab {

    private Collection<NavigationProvider> extensions;
    private ConsoleTab base;

    public ExtendableConsoleTab(ConsoleTab base) {
        this.base = base;
    }

    public String getLabel() {
        return base.getLabel();
    }

    public Class<? extends Page> getModuleHomePage() {
        return base.getModuleHomePage();
    }

    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> items = base.getItems();

        for (NavigationProvider provider : extensions) {
            items.putAll(provider.getItems());
        }

        return items;
    }

    public void setExtensions(Collection<NavigationProvider> extensions) {
        this.extensions = extensions;
    }
}
