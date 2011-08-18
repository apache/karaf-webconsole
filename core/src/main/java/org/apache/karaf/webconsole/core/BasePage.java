package org.apache.karaf.webconsole.core;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.internal.LanguagePanel;
import org.apache.karaf.webconsole.core.navigation.ConsoleTab;
import org.apache.karaf.webconsole.core.navigation.markup.NavigationPanel;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class BasePage extends WebPage {

    @PaxWicketBean(name = "tabs")
    private List<ConsoleTab> tabs;

    // list of supported Locales - should be replaced by resolver/detector or something similar
    private IModel<List<Locale>> supportedLocales = new ListModel<Locale>(Arrays.asList(Locale.FRENCH, Locale.ENGLISH));

    public BasePage() {
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "style.css"));
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "grid.css"));

        add(new Label("footer", "Apache Karaf Console"));

        add(new Image("karafLogo", new ResourceReference(BasePage.class, "images/karaf-logo.png")));

        add(new LanguagePanel("languagePanel", supportedLocales));

        add(new NavigationPanel("navigationPanel", new ListModel<ConsoleTab>(tabs)));
    }

}
