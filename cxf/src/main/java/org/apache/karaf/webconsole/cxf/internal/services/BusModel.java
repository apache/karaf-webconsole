package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.List;

import org.apache.cxf.Bus;
import org.apache.wicket.model.LoadableDetachableModel;

public class BusModel extends LoadableDetachableModel<Bus> {

    private List<Bus> buses;
    private String id;

    public BusModel(List<Bus> buses, Bus object) {
        super(object);
        this.buses = buses;
        this.id = object.getId();
    }

    public BusModel(List<Bus> buses, String id) {
        this.buses = buses;
        this.id = id;
    }

    @Override
    protected Bus load() {
        for (Bus bus : buses) {
            if (id.equals(bus.getId())) {
                return bus;
            }
        }

        throw new IllegalArgumentException("Bus " + id + " not found");
    }

}
