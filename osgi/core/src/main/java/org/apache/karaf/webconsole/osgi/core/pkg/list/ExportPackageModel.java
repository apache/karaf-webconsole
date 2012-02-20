package org.apache.karaf.webconsole.osgi.core.pkg.list;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Version;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class ExportPackageModel extends LoadableDetachableModel<ExportedPackage> {

    private static final long serialVersionUID = 1L;

    private final PackageAdmin admin;
    private String name;
    private String version;

    public ExportPackageModel(PackageAdmin admin, ExportedPackage object) {
        super(object);
        this.admin = admin;
        name = object.getName();
        version = object.getVersion().toString();
    }

    @Override
    protected ExportedPackage load() {
        ExportedPackage[] packages = admin.getExportedPackages(name);
        if (packages != null) {
            Version vers = new Version(version);
            for (ExportedPackage pkg : packages) {
                if (vers.equals(pkg.getVersion())) {
                    return pkg;
                }
            }
        }
        return null;
    }

}
