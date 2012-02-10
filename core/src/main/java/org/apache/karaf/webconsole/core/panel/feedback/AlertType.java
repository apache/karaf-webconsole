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
package org.apache.karaf.webconsole.core.panel.feedback;

/**
 * Enumeration which contains style names for alert panel. It points directly to
 * bootstrap css classes.
 */
public enum AlertType {

    /**
     * By default alert is orange, we don't need any additional classes.
     */
    WARNING(""),

    /**
     * Red background.
     */
    ERROR("alert-error"),

    /**
     * Green background.
     */
    SUCCESS("alert-success"),

    /**
     * Blue background
     */
    INFO("alert-info");

    private String cssClass;

    AlertType(String css) {
        this.cssClass = css;
    }

    public String getCssClass() {
        return cssClass;
    }
}
