package org.apache.karaf.webconsole.core;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class BasePage extends WebPage {

    @PaxWicketBean(name = "tabs")
    private List<ConsoleTab> tabs;

    public BasePage() {
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "style.css"));
        add(CSSPackageResource.getHeaderContribution(BasePage.class, "grid.css"));

        add(new Label("footer", "Apache Karaf Console"));

        add(new Image("karafLogo", new ResourceReference(BasePage.class, "images/karaf-logo.png")));

        Link linkFr = new Link("goFrench") {
			@Override
			public void onClick() {
				getSession().setLocale(Locale.FRANCE);
			}
		};

        Link linkEn = new Link("goEnglish") {
			@Override
			public void onClick() {
				getSession().setLocale(Locale.ENGLISH);
			}
		};


        add(linkFr);
        linkFr.add(new Image("frenchFlag", new ResourceReference(BasePage.class, "images/french-flag.jpeg")));

        add(linkEn);
        linkEn.add(new Image("englishFlag", new ResourceReference(BasePage.class, "images/english-flag.jpeg")));

        add(new NavigationPanel("navigationPanel", new ListModel<ConsoleTab>(tabs)));

        List<Class> subPages = getSubPages();
        if (subPages != null && subPages.size() > 0) {
            add(new SidebarPanel("sidebar", getClass(), subPages));
        } else {
            add(new Label("sidebar").setRenderBodyOnly(true));
        }
    }

    protected List<Class> getSubPages() {
        return Collections.emptyList();
    }

}
