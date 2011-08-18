package org.apache.karaf.webconsole.core.navigation;

import org.apache.wicket.Page;

/**
 * Console tab is top level extension of webconsole. It may provide new elements
 * to navigation and should have label to be rendered in top menu.
 */
public interface ConsoleTab extends NavigationProvider {

    String getLabel();

    Class<? extends Page> getModuleHomePage();

}
