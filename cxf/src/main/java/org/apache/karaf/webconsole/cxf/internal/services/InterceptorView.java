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
package org.apache.karaf.webconsole.cxf.internal.services;

import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * List of interceptors.
 */
public class InterceptorView extends ListView<Interceptor<?>> {

    private static final long serialVersionUID = 1L;

    public InterceptorView(String id, List<Interceptor<?>> interceptors) {
        super(id, interceptors);
    }

    @Override
    protected void populateItem(ListItem<Interceptor<?>> item) {
        Interceptor<? extends Message> interceptor = item.getModelObject();

        item.add(new Label("class", interceptor.getClass().getName()));

        if (interceptor instanceof PhaseInterceptor) {
            PhaseInterceptor<?> phaseInterceptor = (PhaseInterceptor<?>) interceptor;
            item.add(new Label("phase", phaseInterceptor.getPhase()));
            item.add(new Label("id", phaseInterceptor.getId()));
        } else {
            item.add(new Label("phase", "no phase interceptor"));
            item.add(new Label("id", "unknown"));
        }
    }

}
