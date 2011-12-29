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
package org.apache.karaf.webconsole.karaf.features.repository;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.form.LabelBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class AddRepositoryForm extends Form<Repository> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    private TextField<String> uri;

    public AddRepositoryForm(String id) {
        super(id);

        uri = new TextField<String>("uri", Model.of("mvn:")) {
            @Override
            public boolean isRequired() {
                return true;
            }
        };

        LabelBorder border = new LabelBorder("border");
        border.add(new Label("label", "Repository URI"));
        border.add(uri);

        add(border);

        add(new SubmitLink("submit"));
    }

    @Override
    protected void onSubmit() {
        String add = uri.getModelObject();
        try {
            URI uri = new URI(add);
            featuresService.addRepository(uri);

            getSession().info("New repository with uri " + uri + " added");
            getRequestCycle().setResponsePage(RepositoriesPage.class);
        } catch (URISyntaxException e) {
            error("Cannot parse give uri " + e.getMessage());
        } catch (Exception e) {
            error("FeaturesService reported an error " + e.getMessage());
        }
    }

}
