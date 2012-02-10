package org.apache.karaf.webconsole.core.navigation.markup;

import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

public class LanguageTopPanel extends AnonymousTopPanel {

    private static final long serialVersionUID = 1L;

    public LanguageTopPanel(String id, IModel<List<Locale>> locales) {
        super(id, locales);

        add(new ListView<Locale>("languages", locales) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Locale> item) {
                final Locale model = item.getModelObject();

                Link<Void> link = new LanguageChangeLink("languageLink", model);
                link.add(getImage("flag", model));
                link.add(getLabel("label", model));
                item.add(link);
            }
        });
    }

    protected Label getLabel(String id, Locale locale) {
        return new Label(id, locale.getDisplayName());
    }

    protected Image getImage(String id, Locale locale) {
        String localeName = locale.getDisplayName(Locale.ENGLISH);
        ResourceReference resource = new ResourceReference(BasePage.class, "images/" + localeName.toLowerCase() + "-flag.png");
        Image image = new Image(id, resource);
        image.add(new SimpleAttributeModifier("width", "20"));
        image.add(new SimpleAttributeModifier("height", "14"));
        image.add(new SimpleAttributeModifier("alt", localeName));
        image.add(new SimpleAttributeModifier("title", localeName));
        return image;
    }
}
