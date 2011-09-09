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

import java.security.Principal;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.wicket.Request;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for JAAS based authentication sessions.
 */
public abstract class JaasWebSession extends WebConsoleSession {

    /**
     * Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(JaasWebSession.class);

    /**
     * Roles.
     */
    protected final Roles roles = new Roles();

    /**
     * Name of current user;
     */
    private String username;

    public JaasWebSession(Request request) {
        super(request);
    }

    public final boolean authenticate(String username, String password) {
        boolean authenticated = false;
        LoginCallbackHandler handler = new LoginCallbackHandler(username, password);

        try {
            LoginContext ctx = new LoginContext(getRealmName(), handler);
            ctx.login();
            authenticated = true;
            this.username = username;

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

    @Override
    public final Roles getRoles() {
        return roles;
    }

    @Override
    public final String getUsername() {
        return username;
    }

    /**
     * Gets name of JAAS realm to use during authentication process.
     */
    protected abstract String getRealmName();

    /**
     * Verify if given JAAS principal assigned after authentication is role or
     * not. The name of the principal will be added to list of roles assigned
     * to this session.
     * 
     * @param principal Principal
     * @return True if principal name should be used as role name.
     */
    protected abstract boolean isRole(Principal principal);


}