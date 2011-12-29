package org.apache.karaf.webconsole.karaf.admin.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.ActionsPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class InstanceActionsPanel extends ActionsPanel<Instance> {

    public InstanceActionsPanel(String id, IModel<Instance> model) {
        super(id, model);
    }

    @Override
    protected List<Link> getLinks(Instance object, String id) {
        List<Link> links = new ArrayList<Link>();

        try {
            if (Instance.STOPPED.equals(object.getState())) {
                links.add(new Link(id) {
                    {
                        add(new Label("label", "Start"));
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
                links.add(new Link(id) {
                    {
                        add(new Label("label", "Stop"));
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
        links.add(new Link(id) {
            {
                add(new Label("label", "Destroy"));
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
