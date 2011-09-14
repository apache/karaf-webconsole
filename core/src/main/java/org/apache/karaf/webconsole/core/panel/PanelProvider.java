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
package org.apache.karaf.webconsole.core.panel;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Implementation of this interface are able to provide panel blocks.
 */
public interface PanelProvider {

    /**
     * Create new panel with given id. This method will be called during page
     * or parent component rendering - normally in container dispatching thread.
     * 
     * @param id Panel id.
     * @return Panel to be put as widget.
     */
    public Panel createPanel(String id);

}