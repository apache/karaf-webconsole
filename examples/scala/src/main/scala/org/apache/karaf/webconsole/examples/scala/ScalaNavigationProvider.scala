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

import java.util._
import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider
import org.apache.karaf.webconsole.core.util.LinkUtils
import collection.JavaConversions._
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.Page

/**
 * Navigation provider.
 */
class ScalaNavigationProvider extends ConsoleTabProvider {

  def getModuleLink(linkId : String, labelId : String) : Link[Page] = {
    LinkUtils.createPageLink(linkId, labelId, "Examples", classOf[ExampleScalaPage])
  }

  def getItems(linkId : String, labelId : String) : List[Link[Page]] = new ArrayList[Link[Page]];

}
