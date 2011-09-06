package org.apache.karaf.webconsole.camel.internal.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.CamelPage;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.camel.internal.tracking.Tracer;
import org.apache.karaf.webconsole.core.table.map.MapDataProvider;
import org.apache.karaf.webconsole.core.table.map.MapDataTable;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class DumpPage extends CamelPage {

    public DumpPage(TraceContainer container, CamelContext context) {
        Tracer tracer = container.getTracer(context);
        List<Map<String, Serializable>> info;

        if (tracer != null) {
            info = tracer.getInfo();
        } else {
            Session.get().warn("Tracer not found");
            info = new ArrayList<Map<String,Serializable>>();
        }

        add(new ListView<Map<String, Serializable>>("properties", info) {
            @Override
            protected void populateItem(ListItem<Map<String, Serializable>> item) {
                item.add(new MapDataTable<String, Serializable>("propertyMap", new MapDataProvider<String, Serializable>(item.getModelObject()), 20));
            }
        });

    }

}
