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
import java.util.List;

import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@PaxWicketMountPoint(mountPoint = "/osgi/package")
public class PackagePage extends OsgiPage {

    @PaxWicketBean(name = "packageAdmin")
    private PackageAdmin admin;

    public PackagePage(PageParameters params) {
        List<IColumn<ExportedPackage>> columns = new ArrayList<IColumn<ExportedPackage>>();
        columns.add(new OrdinalColumn<ExportedPackage>());
        columns.add(new PropertyColumnExt<ExportedPackage>("Package name", "name"));
        columns.add(new PropertyColumnExt<ExportedPackage>("Version", "version.toString"));
        columns.add(new PropertyColumnExt<ExportedPackage>("Provider", "exportingBundle.symbolicName"));
        add(new DefaultDataTable<ExportedPackage>("packages", columns, new PackageDataProvider(admin), Integer.MAX_VALUE));
    }

}
