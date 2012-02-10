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

import static org.apache.wicket.markup.html.CSSPackageResource.getHeaderContribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.karaf.webconsole.core.behavior.BootstrapBehavior;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.image.Image;

/**
 * Default brand customization.
 */
public class DefaultBrandProvider implements BrandProvider, Serializable /* for tests mainly */ {

    private static final long serialVersionUID = 1L;

    public Image getHeaderImage(String imageId) {
        Image image = new Image(imageId, new ResourceReference(BasePage.class, "images/karaf-logo-min.png"));
        image.add(new SimpleAttributeModifier("width", "148"));
        image.add(new SimpleAttributeModifier("height", "40"));
        image.add(new SimpleAttributeModifier("alt", "Karaf logo"));
        image.add(new SimpleAttributeModifier("title", "Karaf logo"));
        return image;
    }

    public List<IBehavior> getBehaviors() {
        List<IBehavior> behaviors = new ArrayList<IBehavior>();
        behaviors.add(new BootstrapBehavior());
        return behaviors;
    }

    public void modify(Page page) {
        page.add(getHeaderContribution(BasePage.class, "style.css"));
    }

}
