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

import static org.easymock.EasyMock.getCurrentArguments;

import java.io.Serializable;

import org.easymock.IAnswer;

/**
 * Base class for link answers in many providers..
 */
public abstract class BaseLinkAnswer<T> implements IAnswer<T>, Serializable {

    public final T answer() throws Throwable {
        String linkId = (String) getCurrentArguments()[0];
        String labelId = (String) getCurrentArguments()[1];

        return createAnswer(linkId, labelId);
    }

    protected abstract T createAnswer(String linkId, String labelId) throws Throwable;

}
