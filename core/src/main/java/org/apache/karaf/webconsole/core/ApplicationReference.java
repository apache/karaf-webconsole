package org.apache.karaf.webconsole.core;

import org.apache.wicket.Application;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;

public interface ApplicationReference {

    Application getApplication();

    Session getSession();

    RequestCycle getRequestCycle();

}
