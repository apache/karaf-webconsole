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
package org.apache.karaf.webconsole.osgi.core.bundle.list;

import static org.apache.wicket.model.Model.of;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.osgi.core.bundle.install.InstallBundlePage;
import org.apache.karaf.webconsole.osgi.core.shared.BundleDataProvider;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.karaf.webconsole.osgi.core.shared.State;
import org.apache.karaf.webconsole.osgi.core.spi.IColumnProvider;
import org.apache.karaf.webconsole.osgi.core.spi.IDecorationProvider;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.framework.Bundle;
import org.osgi.service.startlevel.StartLevel;

/**
 * Bundle list page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/bundle")
public class BundlePage extends OsgiPage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "columnProviders")
    private List<IColumnProvider> columnProviders;

    @PaxWicketBean(name = "decorationProviders")
    private List<IDecorationProvider> decorationProviders;

    @PaxWicketBean(name = "startLevel")
    private StartLevel startLevel;

    public BundlePage() {
        List<IColumn<Bundle>> columns = new ArrayList<IColumn<Bundle>>();
        columns.add(new AbstractColumn<Bundle>(of("")) {
            private static final long serialVersionUID = 1L;

            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, final String componentId, final IModel<Bundle> rowModel) {
                cellItem.add(new DecorationPanel(componentId, rowModel, decorationProviders));
            }
        });
        columns.add(new PropertyColumnExt<Bundle>("Bundle Id", "bundleId"));
        columns.add(new AbstractColumn<Bundle>(of("State")) {
            private static final long serialVersionUID = 1L;

            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, final String componentId, final IModel<Bundle> rowModel) {
                cellItem.add(new Label(componentId, State.of(rowModel.getObject().getState()).name()));
            }
            
        });
        columns.add(new AbstractColumn<Bundle>(of("Start level")) {
            private static final long serialVersionUID = 1L;

            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, final String componentId, final IModel<Bundle> rowModel) {
                cellItem.add(new Label(componentId, of(startLevel.getBundleStartLevel(rowModel.getObject()))));
            }
            
        });

        for (IColumnProvider provider : columnProviders) {
            columns.add(provider.getColumn());
        }

        columns.add(new PropertyColumnExt<Bundle>("Name", "symbolicName"));
        columns.add(new PropertyColumnExt<Bundle>("Version", "version.toString"));
        columns.add(new AbstractColumn<Bundle>(Model.of("Operations")) {
            private static final long serialVersionUID = 1L;

            public void populateItem(Item<ICellPopulator<Bundle>> cellItem, final String componentId, final IModel<Bundle> rowModel) {
                cellItem.add(new BundleActionsPanel(componentId, rowModel));
            }
        });

        add(new DefaultDataTable<Bundle>("bundles", columns, new BundleDataProvider(context), 100));

        add(new BookmarkablePageLink<Page>("install", InstallBundlePage.class));
    }

}
