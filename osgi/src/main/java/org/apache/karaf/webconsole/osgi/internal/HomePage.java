package org.apache.karaf.webconsole.osgi.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.startlevel.StartLevel;

/**
 * Homepage
 */
public class HomePage extends BasePage {

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;

    // TODO Add any page properties or variables here

    interface ItemClassModifier {
        List<String> getCssClasses(Bundle bundle);
    }

    class SystemBundleClassModifier implements ItemClassModifier {

        public List<String> getCssClasses(Bundle bundle) {
            List<String> classes = new ArrayList<String>();

            if (bundle.getBundleId() == 0) {
                classes.add("framework");
            }

            ServiceReference startLevelReference = context.getServiceReference(StartLevel.class.getName());

            if (startLevelReference != null) {
                StartLevel startLevel = (StartLevel) context.getService(startLevelReference);

                int bundleStartLevel = startLevel.getBundleStartLevel(bundle);
                if (bundleStartLevel < startLevel.getInitialBundleStartLevel()) {
                    classes.add("system");
                }
                context.ungetService(startLevelReference);
            }

            return classes;
        }
    }

    class BlueprintClassModifier implements ItemClassModifier {

        public List<String> getCssClasses(Bundle bundle) {
            List<String> classes = new ArrayList<String>();

            ServiceReference[] registeredServices = bundle.getRegisteredServices();
            if (registeredServices != null) {
                for (ServiceReference reference : registeredServices) {
                    List<String> oc = Arrays.asList((String[]) reference.getProperty("objectClass"));

                    if (oc.contains("org.osgi.service.blueprint.container.BlueprintContainer")) {
                        classes.add("blueprint");
                        break;
                    }

                }
            }

            return classes;
        }

    }

    class SpringClassModifier implements ItemClassModifier {

        public List<String> getCssClasses(Bundle bundle) {
            List<String> classes = new ArrayList<String>();

            ServiceReference[] registeredServices = bundle.getRegisteredServices();
            if (registeredServices != null) {
                for (ServiceReference reference : registeredServices) {
                    List<String> oc = Arrays.asList((String[]) reference.getProperty("objectClass"));

                    if (oc.contains("org.springframework.context.ApplicationContext")) {
                        classes.add("spring");
                        break;
                    }

                }
            }

            return classes;
        }

    }

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters Page parameters
     */
    public HomePage() {
        final List<ItemClassModifier> modifiers = new ArrayList<HomePage.ItemClassModifier>();
        modifiers.add(new SystemBundleClassModifier());
        modifiers.add(new BlueprintClassModifier());
        modifiers.add(new SpringClassModifier());

        List<Bundle> model = context == null ? new ArrayList() : Arrays.asList(context.getBundles());

        add(new ListView<Bundle>("bundles", model) {
            @Override
            protected void populateItem(ListItem<Bundle> item) {
                final Bundle bundle = item.getModelObject();
                item.add(new Label("id", "" + bundle.getBundleId()));
                item.add(new Label("symbolicName", bundle.getSymbolicName()));
                item.add(new Label("version", bundle.getVersion().toString()));

                List<String> classes = new ArrayList<String>() {
                    @Override
                    public String toString() {
                        String toString = "";
                        for (String item : this) {
                            toString += " " + item;
                        }
                        return toString;
                    }
                };

                for (ItemClassModifier modifier : modifiers) {
                    classes.addAll(modifier.getCssClasses(bundle));
                }

                item.add(new SimpleAttributeModifier("class", classes.toString()));
                item.add(new Link("link") {
                    @Override
                    public void onClick() {
                        PageParameters params = new PageParameters();
                        params.put("bundleId", bundle.getBundleId());
                        setResponsePage(new DetailsPage(params));
                    }
                });
            }
        });

    }
}
