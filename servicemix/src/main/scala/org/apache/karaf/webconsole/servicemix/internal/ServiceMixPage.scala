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
package org.apache.karaf.webconsole.servicemix.internal

import org.apache.karaf.webconsole.core.BasePage
import org.apache.karaf.webconsole.core.table.{PropertyColumnExt, OrdinalColumn}
import org.apache.servicemix.nmr.api.{NMR, Endpoint}
import org.ops4j.pax.wicket.api.PaxWicketBean
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import java.util._
import collection.JavaConversions._
import org.apache.wicket.model.{Model}
import org.apache.wicket.extensions.markup.html.repeater.data.table.{IColumn, DefaultDataTable}
import collection.mutable.{ListBuffer}
import org.apache.wicket.PageParameters

class ServiceMixPage extends BasePage {

  @PaxWicketBean private val nmr : NMR = _

  val columns = Array[IColumn[Map[String, Any]]](
    new OrdinalColumn[Map[String, Any]](),
    new PropertyColumnExt[Map[String, Any]]("Name", Endpoint.NAME),
    new PropertyColumnExt[Map[String, Any]]("Version", Endpoint.VERSION),
    new PropertyColumnExt[Map[String, Any]]("Endpoint Name", Endpoint.ENDPOINT_NAME),
    new PropertyColumnExt[Map[String, Any]]("Interface", Endpoint.INTERFACE_NAME),
    new PropertyColumnExt[Map[String, Any]]("Service name", Endpoint.SERVICE_NAME),
    new PropertyColumnExt[Map[String, Any]]("Sync?", Endpoint.CHANNEL_SYNC_DELIVERY),
    new PropertyColumnExt[Map[String, Any]]("Untargetable?", Endpoint.UNTARGETABLE),
    new PropertyColumnExt[Map[String, Any]]("Wsdl url", Endpoint.WSDL_URL)
  )

  val provider = new SortableDataProvider[Map[String, Any]]() {
    override def model(properties : Map[String, Any]) = Model.ofMap(properties)

    override def size() = nmr.getEndpointRegistry.getServices.size

    override def iterator(first: Int, count: Int) = {
      val endpoints = nmr.getEndpointRegistry.getServices

      val b = new ListBuffer[Map[String, Any]]
      for (endpoint <- endpoints) {
        b.add(nmr.getEndpointRegistry.getProperties(endpoint).asInstanceOf[Map[String, Any]])
      }

      b.slice(first, first + count).iterator
    }
  }

  add(new DefaultDataTable[Map[String, Any]]("endpoints", columns, provider, 20));

}
