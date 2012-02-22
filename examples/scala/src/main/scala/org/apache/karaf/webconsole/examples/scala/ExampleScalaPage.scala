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
package org.apache.karaf.webconsole.examples.scala

import javax.management.MBeanServer
import org.apache.karaf.webconsole.core.page.SinglePage
import org.ops4j.pax.wicket.api.PaxWicketBean
import org.ops4j.pax.wicket.api.PaxWicketMountPoint
import java.util._
import collection.JavaConversions._
import org.apache.wicket.markup.html.list.{ListItem, ListView}
import org.apache.wicket.markup.html.basic.Label

/**
 * An example page which is implemented with Scala and uses Pax-Wicket with blueprint
 * to obtain service reference from OSGi.
 */
@PaxWicketMountPoint(mountPoint = "/examples/scala")
class ExampleScalaPage extends SinglePage {

  @PaxWicketBean(name="mbeanServer") private var server : MBeanServer = _

  add(new Label("version", scala.util.Properties.versionString).setRenderBodyOnly(true))

  // use ArrayList to avoid serialization exceptions, sorry... scala!
  add(new ListView[String]("domains", new ArrayList[String](server.getDomains().view)) {
    @Override
    protected def populateItem(item : ListItem[String]) {
        item.add(new Label("domain", item.getModelObject()));
    }
  });

}
