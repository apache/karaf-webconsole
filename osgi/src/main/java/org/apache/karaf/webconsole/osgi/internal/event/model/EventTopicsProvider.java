package org.apache.karaf.webconsole.osgi.internal.event.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class EventTopicsProvider  extends SortableDataProvider<EventTopicInfo> {

    private Map<String, EventTopicInfo> topics = new HashMap<String, EventTopicInfo>();

    public EventTopicsProvider(BundleContext context) {
        try {
            ServiceReference[] references = context.getAllServiceReferences(EventHandler.class.getName(), null);
            if (references == null) {
                return;
            }
            for (ServiceReference reference : references) {
                Object topics = reference.getProperty(EventConstants.EVENT_TOPIC);
                if ("*".equals(topics)) {
                    // do not track all listeners
                    continue;
                } else if (topics instanceof String[]) {
                    for (String topic : (String[]) topics) {
                        handleTopic(topic);
                    }
                } else if (topics instanceof String) { // then String
                    handleTopic((String) topics);
                }
            }
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
    }

    private void handleTopic(String topic) {
        if (!topics.containsKey(topic)) {
            topics.put(topic, new EventTopicInfo(topic, 0)); // it will be increased in next line
        }
        topics.get(topic).addConsumer();
    }

    public Iterator<? extends EventTopicInfo> iterator(int first, int count) {
        return topics.values().iterator();
    }

    public int size() {
        return topics.size();
    }

    public IModel<EventTopicInfo> model(EventTopicInfo object) {
        return Model.of(object);
    }

}
