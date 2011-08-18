package org.apache.karaf.webconsole.core.brand;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.image.Image;

public class DefaultBrandProvider implements BrandProvider {

    public Image getHeaderImage(String imageId) {
        Image image = new Image(imageId, new ResourceReference(BasePage.class, "images/karaf-logo.png"));
        image.add(new SimpleAttributeModifier("width", "150"));
        image.add(new SimpleAttributeModifier("height", "70"));
        image.add(new SimpleAttributeModifier("alt", "Karaf logo"));
        image.add(new SimpleAttributeModifier("title", "Karaf logo"));
        return image;
    }

    public List<IBehavior> getBehaviors() {
        return Collections.emptyList();
    }

}
