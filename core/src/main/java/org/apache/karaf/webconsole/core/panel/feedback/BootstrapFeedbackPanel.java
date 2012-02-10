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

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * Feedback panel which produces bootstrap-like message entries.
 */
public class BootstrapFeedbackPanel extends FeedbackPanel {

    private static final long serialVersionUID = 1L;

    public BootstrapFeedbackPanel(String id) {
        super(id);
    }

    @Override
    protected Component newMessageDisplayComponent(String id, FeedbackMessage message) {
        // by default we'll assign alert-error class
        AlertPanel alertPanel = new AlertPanel(id, message.getMessage().toString(), AlertType.ERROR);

        // depends on message level change the css class
        switch (message.getLevel()) {
            case FeedbackMessage.INFO:
                alertPanel.setType(AlertType.INFO);
                break;
            case FeedbackMessage.WARNING:
                alertPanel.setType(AlertType.WARNING);
                break;
            // Not supported in Wicket 1.4
//            case FeedbackMessage.SUCCESS:
//                alertPanel.setType(AlertType.SUCCESS);
//                break;
        }

        return alertPanel;
    }

}