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
package org.apache.karaf.webconsole.core.test;

import org.apache.karaf.webconsole.core.security.WebConsoleSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Dummy session which does not handle authentication, simply returns true for
 * all input values and only one role.
 */
public class AlwaysAuthenticatedWebSession extends WebConsoleSession {

    private Roles roles;

    public AlwaysAuthenticatedWebSession(Request request) {
        super(request);
        roles = new Roles("admin");
    }

    @Override
    public boolean authenticate(String username, String password) {
        return true;
    }

    @Override
    public String getUsername() {
        return "test";
    }

    @Override
    public Roles getRoles() {
        return roles;
    }

}
