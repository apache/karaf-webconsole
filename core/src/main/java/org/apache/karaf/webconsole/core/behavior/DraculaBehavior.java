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
package org.apache.karaf.webconsole.core.behavior;

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Dracula graphing library.
 * http://www.graphdracula.net/
 */
public class DraculaBehavior extends CompositeBehavior {

    private static final long serialVersionUID = 1L;

    public DraculaBehavior() {
        super(new JQueryBehavior(), new RaphaelBehavior());
    }

    @Override
    protected ResourceReference[] getResourceReferences() {
        return new ResourceReference[] {
            new JavaScriptResourceReference(DraculaBehavior.class, "dracula/Curry-1.0.1.js"),
            new JavaScriptResourceReference(DraculaBehavior.class, "dracula/seedrandom.js"),
            new JavaScriptResourceReference(DraculaBehavior.class, "dracula/dracula_graph.js"),
            new JavaScriptResourceReference(DraculaBehavior.class, "dracula/dracula_graffle.js"),
            new JavaScriptResourceReference(DraculaBehavior.class, "dracula/dracula_algorithms.js"),
        };
    }
}

