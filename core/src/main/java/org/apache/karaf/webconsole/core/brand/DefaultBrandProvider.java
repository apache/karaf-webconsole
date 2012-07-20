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
package org.apache.karaf.webconsole.core.brand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.behavior.BootstrapBehavior;
import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Default brand customization.
 */
public class DefaultBrandProvider implements BrandProvider, Serializable /* for tests mainly */ {

    private static final long serialVersionUID = 1L;

    public Image getHeaderImage(String imageId) {
        Image image = new Image(imageId, new PackageResourceReference(BasePage.class, "images/karaf-logo-min.png"));
        image.add(new AttributeModifier("width", "148"));
        image.add(new AttributeModifier("height", "40"));
        image.add(new AttributeModifier("alt", "Karaf logo"));
        image.add(new AttributeModifier("title", "Karaf logo"));
        return image;
    }

    public List<Behavior> getBehaviors() {
        List<Behavior> behaviors = new ArrayList<Behavior>();
        behaviors.add(new BootstrapBehavior());
        return behaviors;
    }

    public void modify(Page page) {
        page.add(new CssBehavior(BasePage.class, "style.css"));
    }

}
