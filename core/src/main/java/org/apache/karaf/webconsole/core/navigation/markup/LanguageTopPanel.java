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

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Top panel which allows to switch session language.
 */
public class LanguageTopPanel extends AnonymousTopPanel {

    private static final long serialVersionUID = 1L;

    public LanguageTopPanel(String id, IModel<List<Locale>> locales) {
        super(id, locales);

        add(new ListView<Locale>("languages", locales) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Locale> item) {
                final Locale model = item.getModelObject();

                Link<Void> link = new LanguageChangeLink("languageLink", model);
                link.add(getImage("flag", model));
                link.add(getLabel("label", model));
                item.add(link);
            }
        });
    }

    protected Label getLabel(String id, Locale locale) {
        return new Label(id, locale.getDisplayName());
    }

    protected Image getImage(String id, Locale locale) {
        String localeName = locale.getDisplayName(Locale.ENGLISH);
        ResourceReference resource = new PackageResourceReference(BasePage.class, "images/" + localeName.toLowerCase() + "-flag.png");
        Image image = new Image(id, resource);
        image.add(new AttributeModifier("width", "20"));
        image.add(new AttributeModifier("height", "14"));
        image.add(new AttributeModifier("alt", localeName));
        image.add(new AttributeModifier("title", localeName));
        return image;
    }
}
