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
package org.apache.karaf.webconsole.servicemix.internal;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.karaf.webconsole.core.page.SinglePage;
import org.apache.karaf.webconsole.core.table.map.MapDataProvider;
import org.apache.karaf.webconsole.core.table.map.MapDataTable;
import org.apache.servicemix.nmr.api.Exchange;
import org.apache.servicemix.nmr.api.Message;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Exchange details page.
 */
public class DetailsPage extends SinglePage {

    public DetailsPage(Exchange object) {
        add(new CssBehavior(DetailsPage.class, "message.css"));

        Map<String, Object> properties = object.getProperties();

        class ExLabel extends Label {
            public ExLabel(String id, Message m) {
                super(id, display(m));
            }
        }

        add(new Label("id", object.getId()));
        add(new MapDataTable<String, Object>("properties", new MapDataProvider<String, Object>(properties), 20));

        Message in = object.getIn(false);
        if (in != null) {
            add(new ExLabel("inBody", in));
            add(new MapDataTable<String, Object>("inHeaders", new MapDataProvider<String, Object>(in.getHeaders()), 20));
        } else {
            add(new Label("inBody", "Input message not available"));
            add(new Label("inHeaders", "Input message not available"));
        }

        Message out = object.getOut(false);
        if (out != null) {
            add(new ExLabel("outBody", out));
            add(new MapDataTable<String, Object>("outHeaders", new MapDataProvider<String, Object>(out.getHeaders()), 20));
        } else {
            add(new Label("outBody", "Output message not available"));
            add(new Label("outHeaders", "Output message not available"));
        }

        Message fault = object.getFault(false);
        if (fault != null) {
            add(new ExLabel("faultBody", fault));
            add(new MapDataTable<String, Object>("faultHeaders", new MapDataProvider<String, Object>(fault.getHeaders()), 20));
        } else {
            add(new Label("faultBody", "Fault message not available"));
            add(new Label("faultHeaders", "Fault message not available"));
        }

        Exception exception = object.getError();
        if (exception != null) {
            add(new Label("exception", exception.toString()));
        } else {
            add(new Label("exception", ""));
        }
    }

    private String display(Message message) {
        try {
            Object object = message.getBody();

            if (object instanceof InputStream) {
                InputStream is = (InputStream) object;
                byte[] data = new byte[is.available()];
                is.mark(0);
                is.read(data);
                is.reset();

                // Heuristic to check if this is a string
                if (isBinary(data)) {
                    return Arrays.toString(data);
                } else {
                    return new String(data);
                }
            } else if (object instanceof Source) {
                StringWriter buffer = new StringWriter();
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform((Source) object, new StreamResult(buffer));
                return buffer.toString();
            } else if (object != null) {
                return "[" + object.getClass().getName() + "]" + object.toString();
            } else {
                return "- no body -";
            }
        } catch (Exception e) {
            return "Error while reading message: " + e.getMessage();
        }
    }

    private static boolean isBinary(byte[] data) {
        if (data.length == 0) {
            return true;
        }
        double prob_bin = 0;
        for (int i = 0; i < data.length; i++) {
            int j = (int) data[i];
            if (j < 32 || j > 127) {
                prob_bin++;
            }
        }
        double pb = prob_bin / data.length;
        return pb > 0.5;
    }
}
