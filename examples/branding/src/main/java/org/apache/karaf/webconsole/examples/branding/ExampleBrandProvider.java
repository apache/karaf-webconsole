package org.apache.karaf.webconsole.examples.branding;

import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.image.Image;

public class ExampleBrandProvider implements BrandProvider {

    public Image getHeaderImage(String imageId) {
        return new Image(imageId, new ResourceReference(ExampleBrandProvider.class, "logo.png"));
    }

    public List<IBehavior> getBehaviors() {
        List<IBehavior> behaviors = new LinkedList<IBehavior>();
        behaviors.add(CSSPackageResource.getHeaderContribution(ExampleBrandProvider.class, "override.css"));
        return behaviors;
    }

}