package org.apache.karaf.webconsole.servicemix.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.servicemix.nmr.api.Endpoint;
import org.apache.servicemix.nmr.api.EndpointRegistry;
import org.apache.servicemix.nmr.api.NMR;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class ServiceMixPage extends SinglePage {

    @PaxWicketBean(name = "nmr")
    private NMR nmr;

    public ServiceMixPage() {
        IColumn[] columns = new IColumn[] {
            new OrdinalColumn<Map<String, Object>>(),
            new PropertyColumnExt<Map<String, Object>>("Name", Endpoint.NAME),
            new PropertyColumnExt<Map<String, Object>>("Version", Endpoint.VERSION),
            new PropertyColumnExt<Map<String, Object>>("Endpoint Name", Endpoint.ENDPOINT_NAME),
            new PropertyColumnExt<Map<String, Object>>("Interface", Endpoint.INTERFACE_NAME),
            new PropertyColumnExt<Map<String, Object>>("Service name", Endpoint.SERVICE_NAME),
            new PropertyColumnExt<Map<String, Object>>("Sync?", Endpoint.CHANNEL_SYNC_DELIVERY),
            new PropertyColumnExt<Map<String, Object>>("Untargetable?", Endpoint.UNTARGETABLE),
            new PropertyColumnExt<Map<String, Object>>("Wsdl url", Endpoint.WSDL_URL)
        };

        ISortableDataProvider<Map<String, Object>> provider = new SortableDataProvider<Map<String, Object>>() {

            public Iterator<? extends Map<String, Object>> iterator(int first, int count) {
                List<Map<String, Object>> props = new ArrayList<Map<String,Object>>();

                EndpointRegistry endpointRegistry = nmr.getEndpointRegistry();
                for (Endpoint endpoint : endpointRegistry.getServices()) {
                    props.add((Map<String, Object>) endpointRegistry.getProperties(endpoint));
                }

                return props.subList(first, count).iterator();
            }

            public int size() {
                return nmr.getEndpointRegistry().getServices().size();
            }

            public IModel<Map<String, Object>> model(Map<String, Object> object) {
                return Model.ofMap(object);
            }
        };

        add(new DefaultDataTable<Map<String, Object>>("endpoints", columns, provider, 20));
    }
}
