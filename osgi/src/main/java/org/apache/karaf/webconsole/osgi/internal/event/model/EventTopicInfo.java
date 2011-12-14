package org.apache.karaf.webconsole.osgi.internal.event.model;

import java.io.Serializable;

public class EventTopicInfo implements Serializable {

    private String topic;
    private int consumers;

    public EventTopicInfo(String topic, int consumers) {
        this.topic = topic;
        this.consumers = consumers;
    }

    public String getTopic() {
        return topic;
    }

    public int getConsumers() {
        return consumers;
    }

    public void addConsumer() {
        consumers++;
    }
}
