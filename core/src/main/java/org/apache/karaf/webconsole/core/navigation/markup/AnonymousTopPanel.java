package org.apache.karaf.webconsole.core.navigation.markup;

import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class AnonymousTopPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "brandProvider")
    private BrandProvider brandProvider;

    public AnonymousTopPanel(String id, IModel<List<Locale>> locales) {
        super(id, locales);

        Link<DashboardPage> homeLink = new BookmarkablePageLink<DashboardPage>("homeLink", DashboardPage.class);
        //homeLink.add(brandProvider.getHeaderImage("logo"));
        add(homeLink);
        add(brandProvider.getHeaderImage("logo"));
    }

}
