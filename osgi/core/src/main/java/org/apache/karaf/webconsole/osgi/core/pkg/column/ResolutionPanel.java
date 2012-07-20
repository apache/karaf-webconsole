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
package org.apache.karaf.webconsole.osgi.core.pkg.column;

import org.apache.felix.utils.manifest.Clause;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Constants;

public class ResolutionPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public ResolutionPanel(String id, IModel<Clause> model) {
        super(id);

        Clause clause = model.getObject();

        String resolution = clause.getDirective(Constants.RESOLUTION_DIRECTIVE);
        if (resolution == null) {
            resolution = Constants.RESOLUTION_MANDATORY;
        }

        String css = "icon-question-sign"; // unknown resolution
        if (Constants.RESOLUTION_MANDATORY.equals(resolution)) {
            css = "icon-lock";
        } else if (Constants.RESOLUTION_OPTIONAL.equals(resolution)) {
            css = "icon-adjust";
        }

        add(new Label("resolution", resolution).setRenderBodyOnly(true));
        Label icon = new Label("icon", "");
        icon.add(new AttributeModifier("class", css));
        add(icon);

        add(new AttributeModifier("title", "Resolution is " + resolution));
    }

}
