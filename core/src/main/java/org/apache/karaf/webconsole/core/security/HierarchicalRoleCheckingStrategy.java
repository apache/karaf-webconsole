/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.karaf.webconsole.core.security;

import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * Role checking strategy which follow the hierarchy.
 */
public class HierarchicalRoleCheckingStrategy implements IRoleCheckingStrategy {

    public boolean hasAnyRole(Roles roles) {
        HierarchicalRoles assigned = new HierarchicalRoles(AuthenticatedWebSession.get().getRoles());

        HierarchicalRoles requested = new HierarchicalRoles(roles);

        return assigned.hasAnyRole(requested);
    }

}
