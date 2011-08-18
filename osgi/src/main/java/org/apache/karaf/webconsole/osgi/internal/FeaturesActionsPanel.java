package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.features.FeaturesService;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;


public class FeaturesActionsPanel extends Panel {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;
    private ExtendedFeature extendedFeature;

    private static ResourceReference INSTALL_IMG = new ResourceReference(FeaturesActionsPanel.class, "images/feature_install.png");
    private static ResourceReference UNINSTALL_IMG = new ResourceReference(FeaturesActionsPanel.class, "images/feature_uninstall.png");

    public FeaturesActionsPanel(String componentId, IModel<?> model) {

        super(componentId);

        this.extendedFeature = (ExtendedFeature)model.getObject();

        Link link = new Link("link") {

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

        add(link);

        // add image to the link
        link.add(new Image("actionButton", getImage(extendedFeature.getState())));


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
