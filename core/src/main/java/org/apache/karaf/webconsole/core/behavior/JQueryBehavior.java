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

import org.apache.wicket.behavior.AbstractHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.JavascriptPackageResource;


/**
 * JQuery behavior. http://jquery.com
 */
public class JQueryBehavior extends AbstractHeaderContributor {

    private static final long serialVersionUID = 1L;

    @Override
    public IHeaderContributor[] getHeaderContributors() {
        return new IHeaderContributor[] {
            JavascriptPackageResource.getHeaderContribution(JQueryBehavior.class, "jquery/jquery-1.7.1.min.js")
        };
    }

}
