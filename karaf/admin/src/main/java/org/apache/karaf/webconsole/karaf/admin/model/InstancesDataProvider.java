package org.apache.karaf.webconsole.karaf.admin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.admin.AdminService;
import org.apache.karaf.admin.Instance;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class InstancesDataProvider extends SortableDataProvider<Instance> {

    private AdminService admin;

    public InstancesDataProvider(AdminService admin) {
        this.admin = admin;
    }

    public Iterator<? extends Instance> iterator(int first, int count) {
        List<Instance> list = new ArrayList<Instance>();
        Collections.addAll(list, admin.getInstances());
        return list.subList(first, count).iterator();
    }

    public int size() {
        return admin.getInstances().length;
    }

    public IModel<Instance> model(Instance object) {
        return new InstanceModel(admin, object);
    }

}
