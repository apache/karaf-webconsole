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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.felix.utils.manifest.Clause;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.karaf.webconsole.osgi.core.manifest.ManifestUtil;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderDataProvider extends BaseDataProvider<Clause> {

    private static final long serialVersionUID = 1L;
    private transient Logger logger = LoggerFactory.getLogger(getClass());
    private transient Clause[] clauses;
    private final Bundle bundle;
    private final String header;

    public HeaderDataProvider(Bundle bundle, String header) {
        this.bundle = bundle;
        this.header = header;

        try {
            clauses = ManifestUtil.getHeader(bundle, header);
        } catch (IOException e) {
            clauses = new Clause[0];
            logger.error("Cannot parse bundle headers", e);
        }
    }

    public Iterator<? extends Clause> iterator(long first, long count) {
        List<Clause> clauses = new ArrayList<Clause>();
        clauses.addAll(Arrays.asList(this.clauses));
        return clauses.subList((int) first, (int) count).iterator();
    }

    public long size() {
        return clauses.length;
    }

    public IModel<Clause> model(Clause object) {
        return new ClauseModel(bundle, header, object);
    }

}
