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

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.karaf.webconsole.osgi.core.bundle.SingleBundlePage;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.RefreshLink;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.ResolveLink;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.StartLink;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.StopLink;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.UninstallLink;
import org.apache.karaf.webconsole.osgi.core.bundle.list.link.UpdateLink;
import org.apache.karaf.webconsole.osgi.core.shared.State;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * Action panel for bundles list.
 */
@SuppressWarnings("rawtypes")
public class BundleActionsPanel extends ActionsPanel<Bundle> {

    private static final long serialVersionUID = 1L;

    public BundleActionsPanel(String componentId, final IModel<Bundle> model) {
        super(componentId, model);

        add(new ExtensionsPanel("extend", model));
    }

    @Override
    protected List<Link> getLinks(Bundle object, String linkId, String labelId) {
        List<Link> links = new ArrayList<Link>();

        // details link
        Link link = SingleBundlePage.createLink(linkId, object);
        link.add(new Label("label", "").add(new AttributeModifier("class", "icon-info-sign")));

        links.add(link);

        switch (State.of(object.getState())) {
        case ACTIVE:
            links.add(createStopLink(linkId, labelId));
            break;
        case INSTALLED:
            // here we do not have break, because start operation will try to
            // resolve imports too
            links.add(createResolveLink(linkId, labelId));
        case RESOLVED:
            links.add(createStartLink(linkId, labelId));
        }

        links.add(createRefreshLink(linkId, labelId));
        links.add(createUpdateLink(linkId, labelId));
        links.add(createUninstallLink(linkId, labelId));

        return links;
    }

    private Link createUninstallLink(String linkId, String labelId) {
        Link link = new UninstallLink(linkId, getModel());
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-eject")));
        return link;
    }

    private Link createRefreshLink(String linkId, String labelId) {
        Link link = new RefreshLink(linkId, getModel());
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-refresh")));
        return link;
    }

    private Link createUpdateLink(String linkId, String labelId) {
        Link link = new UpdateLink(linkId, getModel());
        //link.add(new SimpleAttributeModifier("title", getLocalizer().getString("bundle.update.link", this, getModel())));
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-retweet")));
        return link;
    }

    private Link createResolveLink(String linkId, String labelId) {
        Link link = new ResolveLink(linkId, getModel());
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-step-forward")));
        return link;
    }

    private Link createStartLink(String linkId, String labelId) {
        Link link = new StartLink(linkId, getModel());
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-play")));
        return link;
    }

    private Link createStopLink(String linkId, String labelId) {
        Link link = new StopLink(linkId, getModel());
        link.add(new Label(labelId, "").add(new AttributeModifier("class", "icon-pause")));
        return link;
    }
}
