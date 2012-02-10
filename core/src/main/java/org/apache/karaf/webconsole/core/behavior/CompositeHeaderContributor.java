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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.behavior.AbstractHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;

/**
 * Composite behavior which collects other resources.
 */
public abstract class CompositeHeaderContributor extends AbstractHeaderContributor {

    private static final long serialVersionUID = 1L;

    private IHeaderContributor[] contributors;

    protected CompositeHeaderContributor(IHeaderContributor ...contributors) {
        this.contributors = contributors;
    }

    protected abstract IHeaderContributor[] getOwnHeaderContributors();

    @Override
    public final IHeaderContributor[] getHeaderContributors() {
        List<IHeaderContributor> merge = new ArrayList<IHeaderContributor>();
        Collections.addAll(merge, contributors);
        Collections.addAll(merge, getOwnHeaderContributors());
        return merge.toArray(new IHeaderContributor[merge.size()]);
    }

}
