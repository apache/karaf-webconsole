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
$(function() {
  var graph = new Graph();
  $("tr.component").each(function(key, value) {
    name = $(value).find("td").eq(0).text().trim();
    graph.addNode(name, {label: name});

    deps = $(value).find("ul li");
    $(deps).each(function(index, dependency) {
      graph.addEdge(name, $(dependency).text().trim())
    })
  });

  var layouter = new Graph.Layout.Spring(graph);
  //layouter.layout();
  renderer = new Graph.Renderer.Raphael('graph', graph, $("#graph").width(), $("#graph").height());
  //renderer.draw();
})