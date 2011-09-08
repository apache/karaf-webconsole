package org.apache.karaf.webconsole.cxf.internal.trace;

import java.io.Serializable;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public class TraceInterceptor extends AbstractPhaseInterceptor<Message> implements Serializable {

    private String direction;

    public TraceInterceptor(String phase, String direction) {
        super(phase);
        this.direction = direction;
    }

    public void handleMessage(Message message) throws Fault {
        System.out.println("--> " + direction + " > " + message);
    }

}
