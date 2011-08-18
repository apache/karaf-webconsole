package org.apache.karaf.webconsole.core.navigation.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.karaf.webconsole.core.navigation.NavigationProvider;
import org.apache.wicket.Page;
import org.apache.wicket.model.ResourceModel;

public class LocalizableNavigationProvider implements NavigationProvider {

    private final NavigationProvider provider;


    public LocalizableNavigationProvider(NavigationProvider provider) {
        this.provider = provider;
    }


    public Map<String, Class<? extends Page>> getItems() {
        Map<String, Class<? extends Page>> items = new HashMap<String, Class<? extends Page>>();

        for (Entry<String, Class<? extends Page>> entry : provider.getItems().entrySet()) {
            items.put(new ResourceModel(entry.getKey(), entry.getKey()).getObject(), entry.getValue());
        }

        return items;
    }

}
