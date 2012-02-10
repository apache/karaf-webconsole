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
package org.apache.karaf.webconsole.osgi.log;

import java.io.Serializable;

/**
 * Options POJO, used to configure data provider for logs table.
 */
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    private Priority priority = Priority.Any;

    private String bundleNameFragment;

    private String messageFragment;

    private Long dateFrom = null;

    private Long dateTo = null;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getBundleNameFragment() {
        return bundleNameFragment;
    }

    public void setBundleNameFragment(String bundleNameFragment) {
        this.bundleNameFragment = bundleNameFragment;
    }

    public String getMessageFragment() {
        return messageFragment;
    }

    public void setMessageFragment(String messageFragment) {
        this.messageFragment = messageFragment;
    }

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Options [priority=" + priority + ", bundleNameFragment="
                + bundleNameFragment + ", messageFragment=" + messageFragment
                + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
    }

    

}
