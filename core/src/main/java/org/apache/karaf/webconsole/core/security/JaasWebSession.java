package org.apache.karaf.webconsole.core.security;

import java.io.IOException;
import java.security.Principal;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.karaf.jaas.modules.RolePrincipal;
import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;

public class JaasWebSession extends AuthenticatedWebSession {
    public static final String ROLES_GROUP_NAME = "ROLES";

    public static final String ROLES_PREFIX = "ROLE_";

    public static final String APPLICATION_POLICY_NAME = "karaf";

    private Roles roles = new Roles();

    public JaasWebSession(Request request) {
        super(request);
    }

    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        LoginCallbackHandler handler = new LoginCallbackHandler(username, password);

        try {
            LoginContext ctx = new LoginContext(APPLICATION_POLICY_NAME, handler);
            ctx.login();
            authenticated = true;

            for (Principal p : ctx.getSubject().getPrincipals()) {
                if (p instanceof RolePrincipal) {
                    roles.add(p.getName());
                }
            }
        } catch (LoginException e) {
            authenticated = false;
        }
        return authenticated;
    }

    protected boolean isRole(Principal p) {
        return p.getName().startsWith(ROLES_PREFIX);
    }

    public Roles getRoles() {
        return roles;
    }

    private class LoginCallbackHandler implements CallbackHandler {

        private String username;

        private String password;

        public LoginCallbackHandler(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public void handle(Callback[] callbacks) throws IOException,
                UnsupportedCallbackException {
            for (int i = 0; i < callbacks.length; i++) {
                Callback callback = callbacks[i];
                if (callback instanceof NameCallback) {
                    ((NameCallback) callback).setName(username);
                } else if (callback instanceof PasswordCallback) {
                    PasswordCallback pwCallback = (PasswordCallback) callback;
                    pwCallback.setPassword(password.toCharArray());
                } else {
                    throw new UnsupportedCallbackException(callbacks[i], "Callback type not supported");
                }
            }
        }
    }

}
