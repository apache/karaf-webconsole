package org.apache.karaf.webconsole.osgi.internal.widget;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.osgi.internal.FeaturesPage;
import org.apache.karaf.webconsole.osgi.internal.RepositoriesPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class FeaturesWidgetPanel extends Panel {

    public FeaturesWidgetPanel(String id, FeaturesService service) {
        super(id);

        try {
            Feature[] allFeatures = service.listFeatures();
            Feature[] installed = service.listInstalledFeatures();

            add(new Label("featuresCount", "" + allFeatures.length));
            add(new Label("installedCount", "" + installed.length));
            add(new Label("uninstalledCount", "" + (allFeatures.length - installed.length)));

            add(new BookmarkablePageLink<FeaturesPage>("featuresLink", FeaturesPage.class));
            add(new BookmarkablePageLink<FeaturesPage>("repositoriesLink", RepositoriesPage.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
