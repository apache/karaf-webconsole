package org.apache.karaf.webconsole.core.util;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

/**
 * Utility class to create links.
 */
public abstract class LinkUtils {

    /**
     * Creates new bookmarkable page link to given page class.
     * 
     * @param <T> Type of page.
     * 
     * @param linkId Link element id.
     * @param labelId Inner link element label.
     * @param label Text label.
     * @param page Page class to link.
     * @return Bookmarkable link.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T extends Page> Link<Page> createPageLink(String linkId, String labelId, String label, Class<T> page) {
        Link link = new BookmarkablePageLink<T>(linkId, page);
        link.add(new Label(labelId, label));
        return link;
    }

}
