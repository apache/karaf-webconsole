package org.apache.karaf.webconsole.core.page;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;

public class LoginPage extends BasePage {

    public LoginPage() {
        this(null);
    }

    public LoginPage(PageParameters parameters) {
        add(new SignInPanel("signIn"));
    }

}
