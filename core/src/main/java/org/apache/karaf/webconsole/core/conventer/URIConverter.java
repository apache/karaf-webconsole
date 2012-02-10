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
package org.apache.karaf.webconsole.core.conventer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.apache.wicket.util.convert.converters.AbstractConverter;

/**
 * URI converter.
 */
public class URIConverter extends AbstractConverter {

    private static final long serialVersionUID = 1L;

    public Object convertToObject(String value, Locale locale) {
        try {
            return new URI(value);
        } catch (URISyntaxException e) {
            throw newConversionException("Illegal syntax: " + e.getReason(), value, locale);
        } catch (IllegalArgumentException e) {
            throw newConversionException("Invalid argument: " + e.getMessage(), value, locale);
        }
    }

    @Override
    protected Class<?> getTargetType() {
        return URI.class;
    }

}
