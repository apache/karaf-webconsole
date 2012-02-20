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
package org.apache.karaf.webconsole.osgi.core.pkg;

import java.io.IOException;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.osgi.core.manifest.ManifestUtil;
import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClauseModel extends LoadableDetachableModel<Clause> {

    private static final long serialVersionUID = 1L;
    private Bundle bundle;
    private String header;
    private String name;

    private transient Logger logger = LoggerFactory.getLogger(ClauseModel.class);

    public ClauseModel(Bundle bundle, String header, Clause object) {
        super(object);
        this.name = object.getName();
        this.bundle = bundle;
        this.header = header;
    }

    @Override
    protected Clause load() {
        Clause[] clauses;
        try {
            clauses = ManifestUtil.getHeader(bundle, header);
            for (Clause clause : clauses) {
                if (name.equals(clause.getName())) {
                    return clause;
                }
            }
        } catch (IOException e) {
            logger.error("Unable to load manifest", e);
        }
        return null;
    }

}
