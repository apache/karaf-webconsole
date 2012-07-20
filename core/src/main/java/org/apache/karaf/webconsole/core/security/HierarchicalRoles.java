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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * An extension of roles to support hierarchical roles. Hierarchy is created by
 * splitting dahses in the role names.
 * 
 * The role checking follow the structure with one exception. If assgidned roles
 * do not have a "specific" extension then check will pass. For example, if user
 * have assigned role "test-role" and component requires role "test-role-one"
 * then user will be authorized.
 */
public class HierarchicalRoles {

    /**
     * Helper class.
     */
    class Node {

        private Map<String, Node> nodes = new TreeMap<String, Node>();

        private String name;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Map<String, Node> getNodes() {
            return nodes;
        }
    }

    /**
     * Assgined roles.
     */
    private Map<String, Node> nodes = new TreeMap<String, Node>();

    public HierarchicalRoles(Roles roles) {
        for (String role : roles) {
            String[] path = role.split("-");

            createNode(nodes, new ArrayList<String>(Arrays.asList(path)));
        }
    }

    public HierarchicalRoles() {
        this(new Roles());
    }

    public HierarchicalRoles(String roles) {
        this(new Roles(roles));
    }

    private void createNode(Map<String, Node> nodes, List<String> path) {
        String name = path.remove(0);

        if (!nodes.containsKey(name)) {
            nodes.put(name, new Node(name));
        }

        if (path.size() >= 1) {
            createNode(nodes.get(name).nodes, path);
        }
    }

    public boolean hasAnyRole(HierarchicalRoles requested) {
        // 
        if (requested.getNodes().isEmpty()) {
            return true;
        }

        Map<String, Node> requestedNodes = requested.getNodes();

        return matchesAny(nodes, requestedNodes);
    }

    private static boolean matchesAny(Map<String, Node> assignedNodes, Map<String, Node> requestedNodes) {
        boolean match = false;
        for (Node node : requestedNodes.values()) {
            Node assigned = assignedNodes.get(node.getName());
            if (assigned != null) {
                if (assigned.getNodes().size() > 0) {
                    match = matchesAny(assigned.getNodes(), node.getNodes());
                } else {
                    match = true;
                }
            }
        }

        return match;
    }

    public Map<String, Node> getNodes() {
        return Collections.unmodifiableMap(nodes);
    }
}

