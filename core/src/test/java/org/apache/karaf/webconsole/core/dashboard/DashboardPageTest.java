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
package org.apache.karaf.webconsole.core.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.WebConsoleTest;
import org.apache.karaf.webconsole.core.brand.DefaultBrandProvider;
import org.apache.karaf.webconsole.core.preferences.util.JdkPreferencesService;
import org.apache.karaf.webconsole.core.test.AlwaysAuthenticatedWebSession;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Test which covers dashboard page.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class DashboardPageTest extends WebConsoleTest {

    static class TestWidgetProvider implements WidgetProvider, Serializable {
        private static final long serialVersionUID = 1L;

        public Panel createPanel(String id) {
            return new EmptyPanel(id);
        }
    }

    @Test
    public void testDashboardNoWidgets() {
        Map<String, Object> values = new HashMap<String, Object>();

        ArrayList<WidgetProvider> providers = new ArrayList<WidgetProvider>();
        providers.add(new TestWidgetProvider());

        values.put("widgets", providers);
        values.put("brandProvider", new DefaultBrandProvider());
        values.put("preferencesService", new JdkPreferencesService());

        injector.setValues(values);

        WicketTester tester = new WicketTester(application);
        tester.startPage(DashboardPage.class);

        tester.assertRenderedPage(DashboardPage.class);
        tester.assertListView("widgets", providers);
        tester.assertInvisible("noWidgets");
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return AlwaysAuthenticatedWebSession.class;
    }
}
