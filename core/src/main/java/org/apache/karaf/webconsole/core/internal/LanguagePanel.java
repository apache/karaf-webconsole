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
package org.apache.karaf.webconsole.core.internal;

import java.util.List;
import java.util.Locale;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class LanguagePanel extends Panel {

    public LanguagePanel(String id, IModel<List<Locale>> locales) {
        super(id);

        add(new ListView<Locale>("languages", locales) {
            @Override
            protected void populateItem(final ListItem<Locale> item) {
                final Locale model = item.getModelObject();
                Image flagImage = new Image("flag", new ResourceReference(BasePage.class, "images/" + model.getDisplayName(Locale.ENGLISH).toLowerCase() + "-flag.png"));
                Link<Void> link = new Link<Void>("languageLink") {
                    @Override
                    public void onClick() {
                        getSession().setLocale(model);
                    }
                };
                item.add(link.add(flagImage));
            }
        });
    }

}
