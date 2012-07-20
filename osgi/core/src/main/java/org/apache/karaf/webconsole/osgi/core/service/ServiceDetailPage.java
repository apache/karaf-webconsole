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
package org.apache.karaf.webconsole.osgi.core.service;

import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * Service details page.
 */
public class ServiceDetailPage extends OsgiPage {

    public ServiceDetailPage(PageParameters parameters) {
        long serviceId = parameters.get("id").toLong();

        RepeatingView repeatingView = new RepeatingView("keys");
        add(repeatingView);

        String filter = "(" + Constants.SERVICE_ID + "=" + serviceId + ")";
        try {
            ServiceReference[] references = context.getServiceReferences(null, filter);

            if (references != null && references.length == 1) {
                ServiceReference reference = references[0];

                for(String key : reference.getPropertyKeys()) {
                    WebMarkupContainer container = new WebMarkupContainer(repeatingView.newChildId());
                    container.add(new Label("key", key));
                    container.add(new Label("value", reference.getProperty(key).toString()));
                    repeatingView.add(container);
                }
            }
        } catch (InvalidSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
