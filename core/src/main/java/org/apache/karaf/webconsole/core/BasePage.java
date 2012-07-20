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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.karaf.webconsole.core.navigation.markup.LanguageTopPanel;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Base page for resource loading and keeping same look and feel in extensions.
 */
public class BasePage extends WebPage {

    /**
     * Brand provider responsible for l&f customization.
     */
    @PaxWicketBean(name = "brandProvider")
    protected BrandProvider brandProvider;

    // list of supported Locales - should be replaced by resolver/detector or something similar
    private IModel<List<Locale>> supportedLocales = new ListModel<Locale>(Arrays.asList(Locale.FRENCH, Locale.ENGLISH));

    public BasePage() {
        add(createTopPanel("topPanel"));

        add(new Label("footer", "Apache Karaf Console"));

        for (Behavior behavior : brandProvider.getBehaviors()) {
            add(behavior);
        }
    }

    protected Panel createTopPanel(String id) {
        return new LanguageTopPanel(id, getSupportedLocales());
    }

    @Override
    protected void onConfigure() {
        brandProvider.modify(this);

        super.onConfigure();
    }

    protected IModel<List<Locale>> getSupportedLocales() {
        return supportedLocales;
    }
}
