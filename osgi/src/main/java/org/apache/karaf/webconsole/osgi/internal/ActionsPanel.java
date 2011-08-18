package org.apache.karaf.webconsole.osgi.internal;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ActionsPanel extends Panel {

        private ComponentType type;
        private IModel<?> model;
        private static final String CONTENT_ID = "content";

        @Override
        protected void onConfigure() {
            super.onConfigure();

            if (type.name() == "FEATURES") {
                addOrReplace(getFeaturesActionsPanel());
            }
        }

        public ActionsPanel(String componentId, final IModel<ExtendedFeature> model, ComponentType type) {
            super(componentId, model);
            this.type = type;
            this.model = model;
        }

        private Component getFeaturesActionsPanel() {
            return new FeaturesActionsPanel(CONTENT_ID,model);
        }

}
