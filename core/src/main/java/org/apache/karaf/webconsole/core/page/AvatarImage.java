package org.apache.karaf.webconsole.core.page;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.resource.ByteArrayResource;
import org.osgi.service.prefs.Preferences;

public class AvatarImage extends Image {

    public AvatarImage(String id, Preferences preferences) {
        super(id);

        byte[] avatar = preferences.getByteArray("avatar", new byte[0]);

        if (avatar != null && avatar.length > 0) {
            String contentType = preferences.get("avatar-mime-type", "image/jpg");
            ByteArrayResource resource = new ByteArrayResource(contentType, avatar);
            resource.setCacheable(false);
            setImageResource(resource);
            return;
        }

        setImageResourceReference(new ResourceReference(AvatarImage.class, "apache-avatar.png"));
    }

}
