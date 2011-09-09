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
package org.apache.karaf.webconsole.core.page;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collections;

import org.apache.karaf.webconsole.core.WebConsoleTest;
import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.karaf.webconsole.core.navigation.SidebarProvider;
import org.apache.karaf.webconsole.core.test.AlwaysAuthenticatedWebSession;
import org.apache.karaf.webconsole.core.test.LinkAnswer;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Test sidebar page and sidebar rendering.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SidebarPageTest extends WebConsoleTest {

    @Test
    public void testSidebarWithoutSubItems() {
        WicketTester tester = new WicketTester(application);

        SidebarProvider provider = createMock(SidebarProvider.class);
        expect(provider.getMasterPageLink(anyString(), anyString())).andAnswer(new LinkAnswer("Test link", DashboardPage.class));
        expect(provider.getItems(anyString(), anyString())).andReturn(emptyLinkList());
        expect(provider.getWidgetProviders()).andReturn(Collections.<WidgetProvider>emptyList());

        replay(provider);

        tester.startPage(new TestSidebarPage(provider));
        tester.assertBookmarkablePageLink("sidebar:masterPageLink", DashboardPage.class, "");
        tester.assertLabel("sidebar:masterPageLink:masterPageLabel", "Test link");
        tester.assertListView("sidebar:subPageLinks", emptyLinkList());

        verify(provider);
    }

    @Test(expected = MarkupException.class)
    public void testNoSidebarProvider() {
        WicketTester tester = new WicketTester(application);

        tester.startPage(SidebarPage.class);
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return AlwaysAuthenticatedWebSession.class;
    }

    static class TestSidebarPage extends SidebarPage {
        public TestSidebarPage(SidebarProvider provider) {
            setSidebarProvider(provider);
        }
    }
}
