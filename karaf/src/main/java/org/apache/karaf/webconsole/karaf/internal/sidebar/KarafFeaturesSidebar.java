package org.apache.karaf.webconsole.karaf.internal.sidebar;

import static org.apache.karaf.webconsole.core.util.LinkUtils.createPageLink;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.karaf.webconsole.karaf.internal.feature.FeaturesPage;
import org.apache.karaf.webconsole.karaf.internal.repository.AddRepositoryPage;
import org.apache.karaf.webconsole.karaf.internal.repository.RepositoriesPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class KarafFeaturesSidebar implements SidebarProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Arrays.asList(
            createPageLink(componentId, labelId, "Repositories", RepositoriesPage.class),
            createPageLink(componentId, labelId, "Add repository", AddRepositoryPage.class)
        );
    }

    public Link<Page> getMasterPageLink(String linkId, String labelId) {
        return createPageLink(linkId, labelId, "Features", FeaturesPage.class);
    }

    public List<WidgetProvider> getWidgetProviders() {
        return Collections.emptyList();
    }

}
