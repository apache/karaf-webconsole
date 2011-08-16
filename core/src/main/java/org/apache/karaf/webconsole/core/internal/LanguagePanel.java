package org.apache.karaf.webconsole.core.internal;

import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class LanguagePanel extends Panel {

    public LanguagePanel(String id, IModel<List<Locale>> locales) {
        super(id);

        add(new ListView<Locale>("languages", locales) {
            @Override
            protected void populateItem(final ListItem<Locale> item) {
                final Locale model = item.getModelObject();
                Image flagImage = new Image("flag", new ResourceReference(BasePage.class, "images/" + model.getDisplayName(Locale.ENGLISH).toLowerCase() + "-flag.png"));
                Link<Void> link = new Link<Void>("languageLink") {
                    @Override
                    public void onClick() {
                        getSession().setLocale(model);
                    }
                };
                item.add(link.add(flagImage));
            }
        });
    }

}
