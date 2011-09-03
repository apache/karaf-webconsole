/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authenticated web session which uses JAAS to authenticate user and obtain roles.
 */
public class JaasWebSession extends AuthenticatedWebSession {

    private Logger logger = LoggerFactory.getLogger(JaasWebSession.class);

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
                if (isRole(p)) {
                    roles.add(p.getName());
                }
            }
        } catch (LoginException e) {
            logger.error("Unable to complete JAAS login operation", e);
            authenticated = false;
        }
        return authenticated;
    }

    protected boolean isRole(Principal p) {
        return p instanceof RolePrincipal;
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
