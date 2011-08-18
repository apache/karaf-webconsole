package org.apache.karaf.webconsole.karaf.internal.feature;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;


public class FeaturesActionsPanel extends ActionsPanel<ExtendedFeature> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;
    private ExtendedFeature extendedFeature;

    private static ResourceReference INSTALL_IMG = new ResourceReference(FeaturesActionsPanel.class,"images/feature_install.png");
    private static ResourceReference UNINSTALL_IMG = new ResourceReference(FeaturesActionsPanel.class,"images/feature_uninstall.png");

    public FeaturesActionsPanel(String componentId, IModel<ExtendedFeature> model) {
        super(componentId, model);
    }

    @Override
    protected List<Link> getLinks(ExtendedFeature object, String id) {
        Link link = new Link(id) {

            @Override
            public void onClick() {

                try {
                    switch (extendedFeature.getState()) {
                        case INSTALLED:
                            featuresService.uninstallFeature(extendedFeature.getName());
                            break;
                        case UNINSTALLED:
                            featuresService.installFeature(extendedFeature.getName());
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        link.add(new SimpleAttributeModifier("class", object.getState().name().toLowerCase()));
        link.add(new Label("label"));

        // add image to the link
        return Arrays.asList(link);
    }

    protected ResourceReference getImage(final ExtendedFeature.State action) {
        final ResourceReference image;
        switch (action) {
            case INSTALLED :
                image = UNINSTALL_IMG;
                break;
            case UNINSTALLED :
                image = INSTALL_IMG;
                break;
            default:
                image = INSTALL_IMG;
                break;
        }

        return image;
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
