package org.apache.karaf.webconsole.karaf.core;

import org.apache.karaf.webconsole.core.page.SecuredPage;
import org.apache.karaf.webconsole.karaf.core.model.Karaf;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

@PaxWicketMountPoint(mountPoint = "/karaf")
public class KarafOverviewPage extends SecuredPage {

    public KarafOverviewPage() {
        setDefaultModel(new CompoundPropertyModel<Karaf>(new Karaf()));

        add(new Label("version"));

        add(new Label("home"));
        add(new Label("base"));
        add(new Label("data"));
        add(new Label("instances"));

        add(new Label("localConsole"));
        add(new Label("remoteShell"));
    }

}
