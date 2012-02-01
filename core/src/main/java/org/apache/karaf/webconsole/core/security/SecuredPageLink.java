package org.apache.karaf.webconsole.core.security;

import org.apache.karaf.webconsole.core.page.SecuredPage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class SecuredPageLink<T extends SecuredPage> extends BookmarkablePageLink<T> {

    public SecuredPageLink(String id, Class<T> pageClass) {
        this(id, pageClass, null);
    }

    public SecuredPageLink(String id, Class<T> pageClass, PageParameters parameters) {
        super(id, pageClass, parameters);
    }

    @Override
    public boolean isVisible() {
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        return session.getAuthorizationStrategy().isInstantiationAuthorized(getPageClass());
    }

}
