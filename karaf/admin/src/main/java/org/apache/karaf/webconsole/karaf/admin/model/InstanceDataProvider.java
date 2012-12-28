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
package org.apache.karaf.webconsole.karaf.admin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.admin.AdminService;
import org.apache.karaf.admin.Instance;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;

/**
 * Data provider for instances list.
 */
public class InstanceDataProvider extends BaseDataProvider<Instance> {

    private static final long serialVersionUID = 1L;

    private AdminService admin;

    public InstanceDataProvider(AdminService admin) {
        this.admin = admin;
    }

    public Iterator<? extends Instance> iterator(long first, long count) {
        List<Instance> list = new ArrayList<Instance>();
        Collections.addAll(list, admin.getInstances());
        return list.subList((int) first, (int) count).iterator();
    }

    public long size() {
        return admin.getInstances().length;
    }

    public IModel<Instance> model(Instance object) {
        return new InstanceModel(admin, object);
    }

}
