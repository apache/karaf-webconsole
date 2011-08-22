package org.apache.karaf.webconsole.blueprint.internal;

import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

@PaxWicketMountPoint(mountPoint = "/osgi/blueprint")
public class BlueprintPage extends BasePage {

    @PaxWicketBean(name = "containers")
    private List<ServiceReference> containers;

    public BlueprintPage() {
        add(new ListView<ServiceReference>("containers", new ListModel<ServiceReference>(containers)) {

            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();

                String symbolicName = (String) reference.getProperty("osgi.blueprint.container.symbolicname");
                Version version = (Version) reference.getProperty("osgi.blueprint.container.version");

                item.add(new Label("symbolicName", symbolicName));
                item.add(new Label("version", version.toString()));

                PageParameters params = new PageParameters();
                params.put("bundleId", reference.getBundle().getBundleId());
                item.add(new BookmarkablePageLink<DetailsPage>("details", DetailsPage.class, params));
            }
            
        });

    }

}
