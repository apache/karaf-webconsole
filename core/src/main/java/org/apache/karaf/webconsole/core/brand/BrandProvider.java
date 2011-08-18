package org.apache.karaf.webconsole.core.brand;

import java.util.List;

import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.image.Image;

public interface BrandProvider {

    Image getHeaderImage(String imageId);

    List<IBehavior> getBehaviors();

}
