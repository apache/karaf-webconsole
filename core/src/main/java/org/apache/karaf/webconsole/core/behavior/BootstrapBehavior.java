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

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * A Twitter bootstrap behavior which contains css and javascript resources.
 * http://twitter.github.com/bootstrap
 */
public class BootstrapBehavior extends CompositeBehavior {

    private static final long serialVersionUID = 1L;

    public BootstrapBehavior() {
        super(new JQueryBehavior());
    }

    @Override
    protected ResourceReference[] getResourceReferences() {
        return new ResourceReference[] {
            new CssResourceReference(BootstrapBehavior.class, "bootstrap/css/bootstrap.css"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-alert.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-button.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-carousel.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-collapse.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-dropdown.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-modal.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-scrollspy.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-tab.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-tooltip.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-popover.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-transition.js"),
            new JavaScriptResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-typeahead.js")
        };
    }

}
