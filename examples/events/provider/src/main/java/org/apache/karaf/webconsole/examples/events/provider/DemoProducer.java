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
package org.apache.karaf.webconsole.examples.events.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * Example brand provider which modify console look and feel.
 */
public class DemoProducer {

    private EventAdmin eventAdmin;
    private List<String> topics;
    private AtomicBoolean run = new AtomicBoolean(true);

    public DemoProducer(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                int topicCount = 0;

                while (run.get()) {
                    if (++topicCount >= topics.size()) {
                        topicCount = 0;
                    }
                    Map properties = new HashMap();
                    properties.put("topicCounter", topicCount);
                    properties.put("counter", ++counter);
                    eventAdmin.postEvent(new Event(topics.get(topicCount), properties));

                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        run.set(false);
    }

}