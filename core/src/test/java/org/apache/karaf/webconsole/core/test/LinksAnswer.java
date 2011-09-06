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
package org.apache.karaf.webconsole.core.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.karaf.webconsole.core.util.LinkUtils;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * An easy mock answer which returns list of links.
 */
public class LinksAnswer extends BaseLinkAnswer<List<Link<Page>>> {

    private Map<String, Class<? extends WebPage>> links = new LinkedHashMap<String, Class<? extends WebPage>>();
    private List<Link<Page>> pageLinks;

    public void addLink(String label, Class<? extends WebPage> page) {
        links.put(label, page);
    }

    @Override
    protected List<Link<Page>> createAnswer(String linkId, String labelId) throws Throwable {
        pageLinks = new ArrayList<Link<Page>>();

        for (String label : this.links.keySet()) {
            pageLinks.add(LinkUtils.createPageLink(linkId, labelId, label, this.links.get(label)));
        }

        return pageLinks;
    }

    public List<Link<Page>> getPageLinks() {
        return pageLinks;
    }

}
