package org.apache.karaf.webconsole.blueprint.internal.navigation;

import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.blueprint.internal.BlueprintPage;
import org.apache.karaf.webconsole.core.navigation.NavigationProvider;
import org.apache.wicket.Page;

public class BlueprintNavigationProvider implements NavigationProvider {

    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> items = new HashMap<String, Class<? extends Page>>();
        items.put("blueprint", BlueprintPage.class);
        return items;
    }

}
