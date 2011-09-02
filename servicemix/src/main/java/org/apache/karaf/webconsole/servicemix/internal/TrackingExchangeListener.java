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
package org.apache.karaf.webconsole.servicemix.internal;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import org.apache.servicemix.nmr.api.Exchange;

/**
 * Implementation of tracking listener which keeps last few exchanges.
 */
public class TrackingExchangeListener implements ITrackingExchangeListener, Serializable {

    private final int maxSize;
    private Deque<Exchange> exchanges = new LinkedList<Exchange>();

    public TrackingExchangeListener() {
        this(20);
    }

    public TrackingExchangeListener(int maxSize) {
        this.maxSize = maxSize;
    }

    public void exchangeSent(Exchange exchange) {
        queue(exchange);
    }

    public void exchangeDelivered(Exchange exchange) {
        queue(exchange);
    }

    public void exchangeFailed(Exchange exchange) {
        queue(exchange);
    }

    private void queue(Exchange exchange) {
        if (exchanges.size() == maxSize) {
            exchanges.removeLast();
        }
        exchanges.addFirst(exchange);
    }

    public Deque<Exchange> getExchanges() {
        return exchanges;
    }

}
