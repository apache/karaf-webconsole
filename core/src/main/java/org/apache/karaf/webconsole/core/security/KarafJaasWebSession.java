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

import org.apache.karaf.jaas.modules.RolePrincipal;
import org.apache.wicket.Request;

/**
 * Authenticated web session which uses JAAS to authenticate user and obtain roles.
 */
public class KarafJaasWebSession extends JaasWebSession {

    private static final long serialVersionUID = 1L;

    private static final String KARAF_REALM = "karaf";

    public KarafJaasWebSession(Request request) {
        super(request);
    }

    @Override
    protected boolean isRole(Principal p) {
        return p instanceof RolePrincipal;
    }

    @Override
    protected String getRealmName() {
        return KARAF_REALM;
    }

}
