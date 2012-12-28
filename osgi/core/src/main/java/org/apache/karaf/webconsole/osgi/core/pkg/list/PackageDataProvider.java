/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.osgi.core.pkg.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class PackageDataProvider extends BaseDataProvider<ExportedPackage> {

    private static final long serialVersionUID = 1L;

    private transient ExportedPackage[] packages;

    private final PackageAdmin admin;

    public PackageDataProvider(PackageAdmin admin) {
        this.admin = admin;
        packages = admin.getExportedPackages((Bundle) null);
    }

    public Iterator<? extends ExportedPackage> iterator(long first, long count) {
        List<ExportedPackage> list = new ArrayList<ExportedPackage>();
        Collections.addAll(list, packages);
        return list.subList((int) first, (int) count).iterator();
    }

    public long size() {
        return packages.length;
    }

    public IModel<ExportedPackage> model(ExportedPackage object) {
        return new ExportPackageModel(admin, object);
    }

}
