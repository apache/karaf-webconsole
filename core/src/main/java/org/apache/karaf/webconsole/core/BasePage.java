package org.apache.karaf.webconsole.core;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.karaf.webconsole.core.internal.LanguagePanel;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class BasePage extends WebPage {

    @PaxWicketBean(name = "brandProvider")
    protected BrandProvider brandProvider;

    // list of supported Locales - should be replaced by resolver/detector or something similar
    private IModel<List<Locale>> supportedLocales = new ListModel<Locale>(Arrays.asList(Locale.FRENCH, Locale.ENGLISH));

    @SuppressWarnings("serial")
    public BasePage() {
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "style.css"));
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "grid.css"));

        add(brandProvider.getHeaderImage("logo"));

        add(new Label("footer", "Apache Karaf Console"));

        add(new LanguagePanel("languagePanel", supportedLocales));

        for (IBehavior behavior : brandProvider.getBehaviors()) {
            add(behavior);
        }
    }
}
