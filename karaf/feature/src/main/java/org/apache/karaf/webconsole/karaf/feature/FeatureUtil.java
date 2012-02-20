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
package org.apache.karaf.webconsole.karaf.feature;

import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to work with feature related tasks.
 */
public class FeatureUtil {

    private static Logger logger = LoggerFactory.getLogger(FeatureUtil.class);

    public static Set<String> getAllowedProtocols(BundleContext context) {
        Set<String> protocols = new HashSet<String>();

        try {
            ServiceReference[] references = context.getAllServiceReferences(URLStreamHandlerService.class.getName(),
                "(!(" + URLConstants.URL_HANDLER_PROTOCOL + "=*))");
            for (ServiceReference reference : references) {
                Object property = reference.getProperty(URLConstants.URL_HANDLER_PROTOCOL);
                if (property != null) {
                    protocols.add((String) property);
                }
            }
        } catch (InvalidSyntaxException e) {
            logger.error("Exception during scanning the services", e);
        }

        return protocols;
    }

}
