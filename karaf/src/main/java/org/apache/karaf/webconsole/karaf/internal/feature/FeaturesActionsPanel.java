package org.apache.karaf.webconsole.karaf.internal.feature;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class FeaturesActionsPanel extends ActionsPanel<Feature> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public FeaturesActionsPanel(String componentId, IModel<Feature> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(final Feature object, String id) {
        Link link = new Link(id) {

            @Override
            public void onClick() {

                try {
                    if (isInstalled(object)) {
                        featuresService.uninstallFeature(object.getName());
                    } else {
                        featuresService.installFeature(object.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        link.add(new SimpleAttributeModifier("class", isInstalled(object) ? "installed" : "uninstalled"));
        link.add(new Label("label"));

        // add image to the link
        return Arrays.asList(link);
    }

    protected boolean isInstalled(Feature feature) {
        return featuresService.isInstalled(feature);
    }

    /*

             SubmitLink removeLink = new SubmitLink("install", form) {
                 @Override
                 public void onSubmit()
                 {
                     ExtendedFeature extendedFeature = (ExtendedFeature)ActionPanel.this.getDefaultModelObject();
                     info("Install feature" + extendedFeature);
                     // call features service
                 }
             };
             removeLink.setDefaultFormProcessing(false);
             add(removeLink);*/


}
