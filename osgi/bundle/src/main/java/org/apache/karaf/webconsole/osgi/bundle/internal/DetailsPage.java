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
package org.apache.karaf.webconsole.osgi.bundle.internal;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

@PaxWicketMountPoint(mountPoint = "/osgi/bundle/detail")
public class DetailsPage extends SinglePage {

    @PaxWicketBean(name = "blueprintBundleContext")
    private BundleContext context;
    private long bundleId;

    public DetailsPage(PageParameters params) {
        bundleId = params.getLong("bundleId");
        Bundle bundle = context.getBundle(bundleId);

        add(new Label("name", bundle.getSymbolicName()));

        String object = (String) bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
        if (object == null) object = "";

        add(new ListView<String>("imports", Arrays.asList(object.split(","))) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("importPackage", item.getModel()));
            }
        });

        object = (String) bundle.getHeaders().get(Constants.EXPORT_PACKAGE);
        if (object == null) object = "";
        add(new ListView<String>("exports", Arrays.asList(object.split(","))) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("exportPackage", item.getModel()));
            }
        });

        IModel<List<ServiceReference>> model = new LoadableDetachableModel<List<ServiceReference>>() {
            @Override
            protected List<ServiceReference> load() {
                return Arrays.asList(context.getBundle(bundleId).getServicesInUse());
            }
        };

        add(new ListView<ServiceReference>("servicesInUse", model) {
            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();
                item.add(new Label("serviceInUse", Arrays.toString((String[]) reference.getProperty("objectClass"))));
            }
        });

        model = new LoadableDetachableModel<List<ServiceReference>>() {
            @Override
            protected List<ServiceReference> load() {
                return Arrays.asList(context.getBundle(bundleId).getRegisteredServices());
            }
        };

        add(new ListView<ServiceReference>("servicesExported", model) {
            @Override
            protected void populateItem(ListItem<ServiceReference> item) {
                ServiceReference reference = item.getModelObject();
                item.add(new Label("serviceExported", Arrays.toString((String[]) reference.getProperty("objectClass"))));
            }
        });
    }


}
