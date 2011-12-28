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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.karaf.webconsole.core.brand.DefaultBrandProvider;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.util.tester.WicketTester;
import org.easymock.IAnswer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Test which checks usage of {@link BrandProvider} inside {@link BasePage}.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class BasePageTest extends WebConsoleTest {

    private String imageId;

    @Test
    public void testBrandProvider() {
        BrandProvider brandProvider = createMock(DefaultBrandProvider.class);
        expect(brandProvider.getHeaderImage((String) anyObject())).andAnswer(new IAnswer<Image>() {
            public Image answer() throws Throwable {
                imageId = (String) getCurrentArguments()[0];
                return new Image(imageId);
            }
        });
        expect(brandProvider.getBehaviors()).andReturn(Collections.<IBehavior>emptyList());
        brandProvider.modify(anyObject(Page.class));

        replay(brandProvider);

        Map<String, Object> values = new HashMap<String, Object>();
        values.put("brandProvider", brandProvider);

        injector.setValues(values);

        WicketTester tester = new WicketTester(application);
        tester.startPage(BasePage.class);

        tester.assertVisible("homeLink:" + imageId);

        verify(brandProvider);
    }

}
