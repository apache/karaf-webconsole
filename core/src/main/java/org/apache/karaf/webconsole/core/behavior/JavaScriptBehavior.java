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

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class JavaScriptBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private final JavaScriptResourceReference javaScriptResourceReference;

    public JavaScriptBehavior(Class<?> pageClass, String resource) {
        this(new JavaScriptResourceReference(pageClass, resource));
    }

    public JavaScriptBehavior(JavaScriptResourceReference resourceReference) {
        this.javaScriptResourceReference = resourceReference;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.renderCSSReference(javaScriptResourceReference);
    }

}
