package org.apache.karaf.webconsole.blueprint.internal;

import java.util.List;

import org.apache.karaf.webconsole.blueprint.internal.view.BlueprintDataTable;
import org.apache.karaf.webconsole.core.BasePage;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.ServiceReference;

@PaxWicketMountPoint(mountPoint = "/osgi/blueprint")
public class BlueprintPage extends BasePage {

    @PaxWicketBean(name = "containers")
    private List<ServiceReference> containers;

    public BlueprintPage() {
        add(new BlueprintDataTable("containers", new BlueprintDataProvider(containers), 100));

        /*
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
        */

    }

}
