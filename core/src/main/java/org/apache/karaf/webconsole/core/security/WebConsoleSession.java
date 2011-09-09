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
package org.apache.karaf.webconsole.core.security;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;

/**
 * Base class for web console session implementation.
 */
public abstract class WebConsoleSession extends AuthenticatedWebSession {

    public WebConsoleSession(Request request) {
        super(request);
    }

    public abstract String getUsername();

    public static WebConsoleSession get() {
        return (WebConsoleSession) AuthenticatedWebSession.get();
    }
}
