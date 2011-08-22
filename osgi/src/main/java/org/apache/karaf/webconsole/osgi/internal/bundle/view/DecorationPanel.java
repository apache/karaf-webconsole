package org.apache.karaf.webconsole.osgi.internal.bundle.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.osgi.bundle.IDecorationProvider;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

public class DecorationPanel extends Panel {

    public DecorationPanel(String id, IModel<Bundle> model, List<IDecorationProvider> decorationProviders) {
        super(id, model);

        add(CSSPackageResource.getHeaderContribution(DecorationPanel.class, "decoration.css"));

        List<Component> components = new ArrayList<Component>();
        for (IDecorationProvider provider : decorationProviders) {
            Component decoration = provider.getDecoration("extension", model);
            if (decoration != null) {
                components.add(decoration);
            }
        }

        add(new ListView<Component>("extensions", components) {
            @Override
            protected void populateItem(ListItem<Component> item) {
                item.add(item.getModelObject());
            }
        });

    }

}
