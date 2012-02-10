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
package org.apache.karaf.webconsole.core.internal.preferences;

import org.apache.karaf.webconsole.core.preferences.PreferencesPage;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Form which allows editing webconsole core bundle preferences.
 */
public class SystemPreferencesForm extends Form<Preferences> {

    private static final long serialVersionUID = 1L;

    private transient Logger logger = LoggerFactory.getLogger(SystemPreferencesForm.class);

    private FileUploadField upload;

    public SystemPreferencesForm(String id, IModel<Preferences> model) {
        super(id, model);

        setMultiPart(true);
        add(upload = new FileUploadField("avatar"));
        add(new Button("submit"));
    }

    @Override
    protected void onSubmit() {
        Preferences prefs = getModelObject();

        FileUpload file = upload.getFileUpload();

        prefs.putByteArray("avatar", file.getBytes());
        prefs.put("avatar-mime-type", file.getContentType());

        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            logger.error("Error while flusing user preferences", e);
            Session.get().error("Cannot store avatar");
        }

        RequestCycle requestCycle = RequestCycle.get();
        requestCycle.setRedirect(true);
        requestCycle.setResponsePage(PreferencesPage.class);
    }
}