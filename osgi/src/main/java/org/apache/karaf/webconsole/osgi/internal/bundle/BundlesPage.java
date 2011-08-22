package org.apache.karaf.webconsole.osgi.internal.bundle;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.osgi.internal.OsgiPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;

@PaxWicketMountPoint(mountPoint = "/osgi/bundles")
public class BundlesPage extends OsgiPage {

    public BundlesPage() {

        List<Bundle> model = Arrays.asList(context.getBundles());

        add(new ListView<Bundle>("bundles", model) {
            @Override
            protected void populateItem(ListItem<Bundle> item) {
                final Bundle bundle = item.getModelObject();
                item.add(new Label("id", "" + bundle.getBundleId()));
                item.add(new Label("symbolicName", bundle.getSymbolicName()));
                item.add(new Label("version", bundle.getVersion().toString()));

//                List<String> classes = new ArrayList<String>() {
//                    @Override
//                    public String toString() {
//                        String toString = "";
//                        for (String item : this) {
//                            toString += " " + item;
//                        }
//                        return toString;
//                    }
//                };

//                for (ItemClassModifier modifier : modifiers) {
//                    classes.addAll(modifier.getCssClasses(bundle));
//                }

//                item.add(new SimpleAttributeModifier("class", classes.toString()));


                PageParameters params = new PageParameters();
                params.put("bundleId", bundle.getBundleId());

                item.add(new BookmarkablePageLink<DetailsPage>("link", DetailsPage.class, params));
            }
        });

    }

}
