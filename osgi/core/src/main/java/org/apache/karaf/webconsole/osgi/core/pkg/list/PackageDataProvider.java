package org.apache.karaf.webconsole.osgi.core.pkg.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class PackageDataProvider extends SortableDataProvider<ExportedPackage> {

    private static final long serialVersionUID = 1L;

    private transient ExportedPackage[] packages;

    private final PackageAdmin admin;

    public PackageDataProvider(PackageAdmin admin) {
        this.admin = admin;
        packages = admin.getExportedPackages((Bundle) null);
    }

    public Iterator<? extends ExportedPackage> iterator(int first, int count) {
        List<ExportedPackage> list = new ArrayList<ExportedPackage>();
        Collections.addAll(list, packages);
        return list.subList(first, count).iterator();
    }

    public int size() {
        return packages.length;
    }

    public IModel<ExportedPackage> model(ExportedPackage object) {
        return new ExportPackageModel(admin, object);
    }

}
