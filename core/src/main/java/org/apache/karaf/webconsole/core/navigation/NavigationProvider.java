package org.apache.karaf.webconsole.core.navigation;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Page;

/**
 * Base extension point in console. Allow suppliers to ship new navigation
 * elements which points to new wicket pages.
 */
public interface NavigationProvider extends Serializable {

    /**
     * Return list of pages to add in navigation. Key in collection is element
     * label.
     * 
     * @return Pages to add in navigation.
     */
    Map<String, Class<? extends Page>> getItems();

}
