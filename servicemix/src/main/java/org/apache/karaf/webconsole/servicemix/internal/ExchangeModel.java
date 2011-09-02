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

import org.apache.servicemix.nmr.api.Exchange;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Detachable model of Exchange, prevents it from serialization. In fact exchange
 * is serializable but some of fields contains implementations which are not visible
 * by our class loader, so it is easier to don't import internal NMR stuff.
 */
public class ExchangeModel extends LoadableDetachableModel<Exchange> {

    /**
     * Listener - it keeps references.
     */
    private ITrackingExchangeListener listener;

    /**
     * Unique exchange id.
     */
    private String id;

    public ExchangeModel(ITrackingExchangeListener listener, Exchange object) {
        super(object);

        this.listener = listener;
        this.id = object.getId();
    }

    @Override
    protected Exchange load() {
        for (Exchange ex : listener.getExchanges()) {
            if (id.equals(ex.getId())) {
                return ex;
            }
        }

        throw new IllegalArgumentException("Exchange is no longer available");
    }

}
