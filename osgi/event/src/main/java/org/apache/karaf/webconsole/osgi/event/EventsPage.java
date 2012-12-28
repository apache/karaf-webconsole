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
package org.apache.karaf.webconsole.osgi.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.table.OrdinalColumn;
import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.karaf.webconsole.osgi.event.model.EventTopicInfo;
import org.apache.karaf.webconsole.osgi.event.model.EventTopicsProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * Event admin page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/event")
public class EventsPage extends OsgiPage {

    private static final long serialVersionUID = 1L;

    public EventsPage() {
        List<IColumn<EventTopicInfo, String>> columns = new ArrayList<IColumn<EventTopicInfo, String>>();
        columns.add(new OrdinalColumn<EventTopicInfo>());
        columns.add(new PropertyColumnExt<EventTopicInfo>("Topic", "topic"));
        columns.add(new PropertyColumnExt<EventTopicInfo>("Number of consumers", "consumers"));

        add(new BaseDataTable<EventTopicInfo>("topics", columns, new EventTopicsProvider(context), 100));
    }

}
