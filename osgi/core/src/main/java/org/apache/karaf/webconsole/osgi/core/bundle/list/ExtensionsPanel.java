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

import java.util.List;

import org.apache.karaf.webconsole.osgi.core.spi.IActionProvider;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.osgi.framework.Bundle;

public class ExtensionsPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "actionProviders")
    private List<IActionProvider> actionProviders;

    public ExtensionsPanel(String id, IModel<Bundle> model) {
        super(id, model);

        int visibleExtensions = 0;
        RepeatingView view = new RepeatingView("extensions");
        for (IActionProvider provider : actionProviders) {
            String childId = view.newChildId();
            Component extension = provider.create(childId, model.getObject());
            view.add(extension);
            if (extension != null && extension.isVisible()) {
                visibleExtensions++;
            }
        }

        add(view);
        if (visibleExtensions == 0) {
            setVisible(false);
        }

    }

}
