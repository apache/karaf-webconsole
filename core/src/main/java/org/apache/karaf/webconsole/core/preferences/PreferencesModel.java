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
package org.apache.karaf.webconsole.core.preferences;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;

/**
 * Model which allows to load preferences.
 */
public class PreferencesModel extends LoadableDetachableModel<Preferences> {

    private static final long serialVersionUID = 1L;

    /**
     * Service to load preferences.
     */
    private PreferencesService service;

    /**
     * Path to preference node.
     */
    private String path;

    /**
     * User name. If null then model is used to display system preferences.
     */
    private String user;

    public PreferencesModel(PreferencesService service, Preferences node, String userName) {
        super(node);
        this.service = service;
        this.user = userName;

        this.path = node.absolutePath();
    }

    public PreferencesModel(PreferencesService service, Preferences node) {
        this(service, node, null);
    }

    @Override
    protected Preferences load() {
        try {
            if (user == null) {
                return resolve(service.getSystemPreferences(), path);
            }
            return resolve(service.getUserPreferences(user), path);
        } catch (BackingStoreException e) {
            throw new RuntimeException("Cannot load preferences", e);
        }
    }

    private Preferences resolve(Preferences preferences, String absolutePath) throws BackingStoreException {
        if (preferences.nodeExists(absolutePath)) {
            return preferences.node(absolutePath);
        }

        throw new IllegalArgumentException("Path " + absolutePath + " doesnt exists");
    }


}
