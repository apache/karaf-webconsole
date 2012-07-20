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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.Map;

import org.apache.karaf.webconsole.core.security.HierarchicalRoles.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Tests role inheritance and matching.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class HierarchicalRolesTest {

    @Test
    public void testEmptyRoles() {
        HierarchicalRoles roles = new HierarchicalRoles();
        HierarchicalRoles requested = new HierarchicalRoles("test-user");

        assertEquals(0, roles.getNodes().size());
        assertFalse(roles.hasAnyRole(requested));
        assertTrue(requested.hasAnyRole(roles));
    }

    @Test
    public void testHierarchy() {
        HierarchicalRoles roles = new HierarchicalRoles("test-user, test-dev, test-operator");

        Map<String, Node> nodes = roles.getNodes();
        assertEquals(1, nodes.size());

        nodes = nodes.get("test").getNodes();
        assertEquals(3, nodes.size());

        assertTrue(nodes.containsKey("user"));
        assertTrue(nodes.containsKey("dev"));
        assertTrue(nodes.containsKey("operator"));
    }

    @Test
    public void testMultipleHierarchy() {
        HierarchicalRoles roles = new HierarchicalRoles("test-user, user-one, user-two, test-mock");

        Map<String, Node> nodes = roles.getNodes();
        assertEquals(2, nodes.size());

        Map<String, Node> testNodes = nodes.get("test").getNodes();
        assertEquals(2, testNodes.size());

        assertTrue(testNodes.containsKey("user"));
        assertTrue(testNodes.containsKey("mock"));

        Map<String, Node> userNodes = nodes.get("user").getNodes();
        assertEquals(2, userNodes.size());

        assertTrue(userNodes.containsKey("one"));
        assertTrue(userNodes.containsKey("two"));
    }

    @Test
    public void testHierarchyMatching() {
        HierarchicalRoles assigned = new HierarchicalRoles("test");
        HierarchicalRoles requested = new HierarchicalRoles("test-user");

        assertTrue(assigned.hasAnyRole(requested));
        assertFalse(requested.hasAnyRole(assigned));
    }

    @Test
    public void testTwoLevelHierarchyMatching() {
        HierarchicalRoles assigned = new HierarchicalRoles("test-user");
        HierarchicalRoles requested = new HierarchicalRoles("test-user-one,test-user-two,test-user-four");

        assertTrue(assigned.hasAnyRole(requested));
        assertFalse(requested.hasAnyRole(assigned));

        assigned = new HierarchicalRoles("test-user2");
        assertFalse(assigned.hasAnyRole(requested));
        assertFalse(requested.hasAnyRole(assigned));

        assigned = new HierarchicalRoles("test-user-one");
        assertTrue(assigned.hasAnyRole(requested));
        assertTrue(requested.hasAnyRole(assigned));
    }

    @Test
    public void testMatching() {
        HierarchicalRoles assigned = new HierarchicalRoles("test");
        HierarchicalRoles requested = new HierarchicalRoles("test");

        assertTrue(assigned.hasAnyRole(requested));
        assertTrue(requested.hasAnyRole(assigned));
    }
}
