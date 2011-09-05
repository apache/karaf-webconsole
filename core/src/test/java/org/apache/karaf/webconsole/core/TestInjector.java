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
package org.apache.karaf.webconsole.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.internal.injection.AbstractPaxWicketInjector;
import org.ops4j.pax.wicket.internal.injection.ComponentProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link IComponentInstantiationListener} which injects given values to fields
 * annotated with {@link PaxWicketBean}.
 */
public class TestInjector extends AbstractPaxWicketInjector implements IComponentInstantiationListener {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(TestInjector.class);

    /**
     * Values to inject.
     */
    private final Map<String, Object> values;

    public TestInjector(Map<String, Object> values) {
        this.values = values;
    }

    /**
     * {@inheritDoc}
     */
    public void onInstantiation(Component component) {

        for (Field field : getFields(component.getClass())) {
            // iterate over fields and check if there is values to inject 
            String name = field.getName();
            Class<?> type = field.getType();

            if (values.containsKey(name)) {
                // we have value..
                Object value = values.get(name);

                // create instance proxy, just like pax-wicket does
                if (type.isAssignableFrom(value.getClass())){
                    Enhancer enhancer = new Enhancer();
                    enhancer.setSuperclass(value.getClass());
                    if (!(value instanceof Serializable)) {
                        logger.warn("Value for field {}.{} is not serializable. Adding Serializable interface for test, result in production may vary",
                            field.getDeclaringClass(), name);
                        enhancer.setInterfaces(new Class[] {Serializable.class});
                    }
                    enhancer.setCallback(new ComponentProxy("test", null));
                    setField(component, field, enhancer.create());
                } else {
                    logger.error("Type mismath for field {}.{}. Expected {} but {} given", new Object[] {
                        field.getDeclaringClass().getName(), name, field.getType().getName(), value.getClass().getName()
                    });
                    System.err.println("Unknown type " + field.getType());
                }
            } else {
                logger.warn("No value provided for field {}.{}", field.getDeclaringClass().getName(), name);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void inject(Object toInject, Class<?> toHandle) {
        throw new UnsupportedOperationException("inject operation should never be executed");
    }
}