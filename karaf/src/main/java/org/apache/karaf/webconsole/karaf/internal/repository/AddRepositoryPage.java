package org.apache.karaf.webconsole.karaf.internal.repository;

import org.apache.karaf.webconsole.core.SidebarPage;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/karaf/repositories/add")
public class AddRepositoryPage extends SidebarPage {

    public AddRepositoryPage() {

        add(new AddRepositoryForm("add"));

    }

}
