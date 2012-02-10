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

import static org.apache.wicket.markup.html.JavascriptPackageResource.getHeaderContribution;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderContributor;

/**
 * A Twitter bootstrap behavior which contains css and javascript resources.
 * http://twitter.github.com/bootstrap
 */
public class BootstrapBehavior extends CompositeHeaderContributor {

    private static final long serialVersionUID = 1L;

    public BootstrapBehavior() {
        super(new JQueryBehavior());
    }

    @Override
    protected IHeaderContributor[] getOwnHeaderContributors() {
        return new IHeaderContributor[] {
            CSSPackageResource.getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/css/bootstrap.css")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-alert.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-button.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-carousel.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-collapse.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-dropdown.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-modal.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-scrollspy.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-tab.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-tooltip.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-popover.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-transition.js")),
            getHeaderContribution(new ResourceReference(BootstrapBehavior.class, "bootstrap/js/bootstrap-typeahead.js"))
        };
    }

}
