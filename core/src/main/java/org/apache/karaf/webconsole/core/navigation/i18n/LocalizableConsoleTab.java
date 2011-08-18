package org.apache.karaf.webconsole.core.navigation.i18n;

import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.wicket.Page;
import org.apache.wicket.model.ResourceModel;

public class LocalizableConsoleTab extends LocalizableNavigationProvider implements ConsoleTab {

    private ConsoleTab provider;

    public LocalizableConsoleTab(ConsoleTab provider) {
        super(provider);
        this.provider = provider;
    }

    public String getLabel() {
        return new ResourceModel(provider.getLabel(), provider.getLabel()).getObject();
    }

    public Class<? extends Page> getModuleHomePage() {
        return provider.getModuleHomePage();
    }

}
