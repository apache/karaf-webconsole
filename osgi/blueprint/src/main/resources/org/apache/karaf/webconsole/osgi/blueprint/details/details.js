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