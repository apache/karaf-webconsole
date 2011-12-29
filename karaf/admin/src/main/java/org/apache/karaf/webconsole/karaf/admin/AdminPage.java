package org.apache.karaf.webconsole.karaf.admin;

import org.apache.karaf.admin.AdminService;
import org.apache.karaf.webconsole.core.page.SidebarPage;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class AdminPage extends SidebarPage {

    @PaxWicketBean(name = "adminService")
    protected AdminService admin;

    public AdminPage() {
        setSidebarProvider(new AdminSidebarProvider());
    }

}
