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
package org.apache.karaf.webconsole.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FrameworkService {
    private Logger logger = LoggerFactory.getLogger(FrameworkService.class);

    private final ServletContext context;
    private Felix felix;

    public FrameworkService(ServletContext context) {
        this.context = context;
    }

    public void start() {
        try {
            doStart();
        } catch (Exception e) {
            logger.error("Failed to start framework", e);
        }
    }

    public void stop() {
        try {
            doStop();
        } catch (Exception e) {
            logger.error("Error stopping framework", e);
        }
    }

    private void doStart() throws Exception {
        Felix tmp = new Felix(createConfig());
        tmp.start();
        this.felix = tmp;
        logger.info("OSGi framework started");
    }

    private void doStop() throws Exception {
        if (this.felix != null) {
            this.felix.stop();
        }

        logger.info("OSGi framework stopped");
    }

    private Map<String, Object> createConfig() throws Exception {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/framework.properties"));

        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            map.put(key.toString(), props.get(key));
        }

        map.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, Arrays.asList(new ProvisionActivator(this.context)));
        return map;
    }

}