package org.apache.karaf.webconsole.core;

import org.apache.wicket.Application;
import org.apache.wicket.Session;

/**
 * Helper class which calls {@link Application#set(Application)} with given
 * instance.
 */
public class ApplicationSetter {

    private ApplicationReference reference;

    public ApplicationSetter(ApplicationReference reference) {
        this.reference = reference;
    }

    public void initialize() {
        Application.set(reference.getApplication());
        Session.set(reference.getSession());
    }

    public void destroy() {
        Application.unset();
        Session.unset();
    }

}
