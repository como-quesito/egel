
// To tell D3 you are selecting an element by its id,
// you should use the # sign before the id.
// The result of the selection is assigned to the fds variable.
var fda = d3.select("#fun-drawing-area");
 var stroke_width = 2;
 var stroke_color = "black";
 // Drawing a Circle
 fda.append("circle")
 .attr("cx", 50)
 .attr("cy", 50)
 .attr("r", 40)
 .style("stroke", stroke_color)
 .style("stroke-width", stroke_width)
 .style("fill", "yellow");
 // Drawing a Square, i.e. rectangle with equal sides
 fda.append("rect")
 .attr("x", 100)
 .attr("y", 100)
 .attr("width", 50)
 .attr("height", 50)
 .style("stroke", stroke_color)
 .style("stroke-width", stroke_width)
 .style("fill", "red");