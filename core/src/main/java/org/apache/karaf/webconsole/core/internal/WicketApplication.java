package org.apache.karaf.webconsole.core.internal;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see org.code-house.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

    public WicketApplication() {
        super();
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<DashboardPage> getHomePage()
    {
        return DashboardPage.class;
    }

}
