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
package org.apache.karaf.webconsole.core.navigation.markup;

import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.karaf.webconsole.core.dashboard.DashboardPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Top panel displayed to non authorized users.
 */
public class AnonymousTopPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "brandProvider")
    private BrandProvider brandProvider;

    public AnonymousTopPanel(String id, IModel<List<Locale>> locales) {
        super(id, locales);

        Link<DashboardPage> homeLink = new BookmarkablePageLink<DashboardPage>("homeLink", DashboardPage.class);
        add(brandProvider.getHeaderImage("logo"));
        add(homeLink);
    }

}
