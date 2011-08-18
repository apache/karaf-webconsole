package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.features.FeaturesService;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


public class UninstallFeaturesPanel extends Panel {

        private FeaturesService featuresService;

        public UninstallFeaturesPanel(String id, final IModel<ExtendedFeature> model, final FeaturesService featuresService) {

            super(id, model);

            Link link = new Link("uninstall") {

                @Override
                public void onClick() {
                    try {
                        featuresService.uninstallFeature(model.getObject().getName());
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            };

            // add image to the link
            link.add(new Image("featuresUninstallButton", new ResourceReference(this.getClass(), "images/feature_uninstall.png")));

            /*SubmitLink removeLink = new SubmitLink("install", form) {
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

}
