package org.apache.karaf.webconsole.camel.internal.tracking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.AsyncCallback;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.processor.DelegateAsyncProcessor;

public class TraceProcessor extends DelegateAsyncProcessor {

    private Map<String, Serializable> properties;

    public TraceProcessor(Map<String, Serializable> properties, Processor target) {
        super(target);
        this.properties = properties;
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {

        CamelContext context = exchange.getContext();
        if (!context.isTracing()) {
            return super.process(exchange, callback);
        }

        properties.put("exchangeId", exchange.getExchangeId());

        properties.put("exchangeProperties", (Serializable) exchange.getProperties());
        Message msg = exchange.getIn();
        if (msg != null) {
            properties.put("exchangeInId", msg.getMessageId());
            properties.put("exchangeInHeaders", new HashMap(msg.getHeaders()));
            Object body = msg.getBody();
            if (body instanceof Serializable) {
                properties.put("exchangeInBody", (Serializable) body);
            } else {
                properties.put("exchangeInBody", "- not serializable -");
            }
        } else {
            properties.put("exchangeInId", null);
        }

        msg = exchange.getOut();
        if (msg != null) {
            properties.put("exchangeOutId", msg.getMessageId());
            properties.put("exchangeOutHeaders", new HashMap(msg.getHeaders()));
            Object body = msg.getBody();
            if (body instanceof Serializable) {
                properties.put("exchangeOutBody", (Serializable) body);
            } else {
                properties.put("exchangeOutBody", "- not serializable -");
            }
        } else {
            properties.put("exchangeOutId", null);
        }

        return super.process(exchange, callback);
    }

}
