package org.apache.karaf.webconsole.karaf.admin.model;

import org.apache.karaf.admin.AdminService;
import org.apache.karaf.admin.Instance;
import org.apache.wicket.model.LoadableDetachableModel;

public class InstanceModel extends LoadableDetachableModel<Instance> {

    private AdminService admin;
    private String name;

    public InstanceModel(AdminService admin, Instance object) {
        super(object);
        this.admin = admin;
        this.name = object.getName();
    }

    @Override
    protected Instance load() {
        return admin.getInstance(name);
    }

}
