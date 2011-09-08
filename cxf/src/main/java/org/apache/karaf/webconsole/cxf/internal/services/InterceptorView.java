package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class InterceptorView extends ListView<Interceptor> {

    public InterceptorView(String id, List<Interceptor> interceptors) {
        super(id, interceptors);
    }

    @Override
    protected void populateItem(ListItem<Interceptor> item) {
        Interceptor<? extends Message> interceptor = item.getModelObject();

        item.add(new Label("class", interceptor.getClass().getName()));

        if (interceptor instanceof PhaseInterceptor) {
            PhaseInterceptor phaseInterceptor = (PhaseInterceptor) interceptor;
            item.add(new Label("phase", phaseInterceptor.getPhase()));
            item.add(new Label("id", phaseInterceptor.getId()));
        } else {
            item.add(new Label("phase", "no phase interceptor"));
            item.add(new Label("id", "unknown"));
        }
    }

}
