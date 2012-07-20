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
package org.apache.karaf.webconsole.karaf.admin.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * Instance actions.
 */
public class InstanceActionsPanel extends ActionsPanel<Instance> {

    private static final long serialVersionUID = 1L;

    public InstanceActionsPanel(String id, IModel<Instance> model) {
        super(id, model);
    }

    @Override
    @SuppressWarnings({"serial", "rawtypes"})
    protected List<Link> getLinks(Instance object, String linkId, final String labelId) {
        List<Link> links = new ArrayList<Link>();

        try {
            if (Instance.STOPPED.equals(object.getState())) {
                links.add(new Link(linkId) {
                    {
                        add(new Label(labelId, "Start"));
                    }
                    @Override
                    public void onClick() {
                        Instance instance = (Instance) InstanceActionsPanel.this.getDefaultModelObject();
   
                        try {
                            instance.start("");
                        } catch (Exception e) {
                            Session.get().error("Cannot start instance " + instance.getName());
                        }
                    }
                });
            } else {
                links.add(new Link(linkId) {
                    {
                        add(new Label(labelId, "Stop"));
                    }
                    @Override
                    public void onClick() {
                        Instance instance = (Instance) InstanceActionsPanel.this.getDefaultModelObject();
   
                        try {
                            instance.stop();
                        } catch (Exception e) {
                            Session.get().error("Cannot stop instance " + instance.getName());
                        }
                    }
                });
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        links.add(new Link(linkId) {
            {
                add(new Label(labelId, "Destroy"));
            }
            @Override
            public void onClick() {
                Instance instance = (Instance) InstanceActionsPanel.this.getDefaultModelObject();

                try {
                    instance.destroy();
                } catch (Exception e) {
                    Session.get().error("Cannot destroy instance " + instance.getName());
                }
            }
        });

        return links;
    }
}
