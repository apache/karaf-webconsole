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
package org.apache.karaf.webconsole.core;

import java.util.ArrayList;

import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.karaf.webconsole.core.page.LoginPage;
import org.apache.karaf.webconsole.core.widget.WidgetProvider;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Test login operation and role verification.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class LoginTest extends WebConsoleTest {

    @Test
    public void testSuccessLogin() throws Exception {
        injector.registerBean("widgets", new ArrayList<WidgetProvider>());

        tester.startPage(LoginPage.class);

        FormTester form = tester.newFormTester("signIn:signInForm");

        form.setValue("username", "rootadmin");
        form.setValue("password", "admin");
        form.submit();

        tester.assertNoErrorMessage();
        tester.assertRenderedPage(DashboardPage.class);
    }

    @Test(expected = UnauthorizedInstantiationException.class)
    public void testWrongRoleLogin() throws Exception {
        tester.startPage(LoginPage.class);

        FormTester form = tester.newFormTester("signIn:signInForm");

        form.setValue("username", "someuser");
        form.setValue("password", "user");
        form.submit();
    }

}
