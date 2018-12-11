package fschmidt.javad3;

import java.util.Arrays;

/**
 *
 * @author fschmidt
 */
public class HistogramPlot extends D3Plot {

    private final double[] values;
    public HistogramPlot(double[] values){
        super(Version.V3);
        this.values = values;
    }
    
    @Override
    protected String getJavascriptFile(String... d3jsPaths) {
        String d3jsPath = d3jsPaths[0];
        String javascriptFile = "<!DOCTYPE html>\n" + "<html>\n" + "  <head>\n" + "    <title>The d3 test</title>\n"
                + "    <script type='text/javascript' src='" + d3jsPath + "' charset=\"utf-8\"></script>\n"
                + "  </head>\n" + "  <body>\n" + "<script>\n" + "\n" + "var color = \"steelblue\";\n" + "\n"
                + "var values = "+Arrays.toString(values)+";\n" + "\n" + "// A formatter for counts.\n"
                + "var formatCount = d3.format(\",.0f\");\n" + "\n" + "var margin = {top: 20, right: 30, bottom: 30, left: 30},\n"
                + "    width = 960 - margin.left - margin.right,\n" + "    height = 500 - margin.top - margin.bottom;\n" + "\n"
                + "var max = d3.max(values);\n" + "var min = d3.min(values);\n" + "var x = d3.scale.linear()\n"
                + "      .domain([min, max])\n" + "      .range([0, width]);\n" + "\n"
                + "// Generate a histogram using twenty uniformly-spaced bins.\n" + "var data = d3.layout.histogram()\n"
                + "    .bins(x.ticks(20))\n" + "    (values);\n" + "\n" + "var yMax = d3.max(data, function(d){return d.length});\n"
                + "var yMin = d3.min(data, function(d){return d.length});\n" + "var colorScale = d3.scale.linear()\n"
                + "            .domain([yMin, yMax])\n" + "            .range([d3.rgb(color).brighter(), d3.rgb(color).darker()]);\n" + "\n"
                + "var y = d3.scale.linear()\n" + "    .domain([0, yMax])\n" + "    .range([height, 0]);\n" + "\n"
                + "var xAxis = d3.svg.axis()\n" + "    .scale(x)\n" + "    .orient(\"bottom\");\n" + "\n"
                + "var svg = d3.select(\"body\").append(\"svg\")\n" + "    .attr(\"width\", width + margin.left + margin.right)\n"
                + "    .attr(\"height\", height + margin.top + margin.bottom)\n" + "  .append(\"g\")\n"
                + "    .attr(\"transform\", \"translate(\" + margin.left + \",\" + margin.top + \")\");\n" + "\n"
                + "var bar = svg.selectAll(\".bar\")\n" + "    .data(data)\n" + "  .enter().append(\"g\")\n"
                + "    .attr(\"class\", \"bar\")\n"
                + "    .attr(\"transform\", function(d) { return \"translate(\" + x(d.x) + \",\" + y(d.y) + \")\"; });\n" + "\n"
                + "bar.append(\"rect\")\n" + "    .attr(\"x\", 1)\n" + "    .attr(\"width\", (x(data[0].dx) - x(0)) - 1)\n"
                + "    .attr(\"height\", function(d) { return height - y(d.y); })\n"
                + "    .attr(\"fill\", function(d) { return colorScale(d.y) });\n" + "\n" + "bar.append(\"text\")\n"
                + "    .attr(\"dy\", \".75em\")\n" + "    .attr(\"y\", -12)\n" + "    .attr(\"x\", (x(data[0].dx) - x(0)) / 2)\n"
                + "    .attr(\"text-anchor\", \"middle\")\n" + "    .text(function(d) { return formatCount(d.y); });\n" + "\n"
                + "svg.append(\"g\")\n" + "    .attr(\"class\", \"x axis\")\n"
                + "    .attr(\"transform\", \"translate(0,\" + height + \")\")\n" + "    .call(xAxis);\n" + "\n" + "/*\n"
                + "* Adding refresh method to reload new data\n" + "*/\n" + "function refresh(values){\n"
                + "  // var values = d3.range(1000).map(d3.random.normal(20, 5));\n" + "  var data = d3.layout.histogram()\n"
                + "    .bins(x.ticks(20))\n" + "    (values);\n" + "\n" + "  // Reset y domain using new data\n"
                + "  var yMax = d3.max(data, function(d){return d.length});\n"
                + "  var yMin = d3.min(data, function(d){return d.length});\n" + "  y.domain([0, yMax]);\n"
                + "  var colorScale = d3.scale.linear()\n" + "              .domain([yMin, yMax])\n"
                + "              .range([d3.rgb(color).brighter(), d3.rgb(color).darker()]);\n" + "\n"
                + "  var bar = svg.selectAll(\".bar\").data(data);\n" + "\n" + "  // Remove object with data\n" + "  bar.exit().remove();\n"
                + "\n" + "  bar.transition()\n" + "    .duration(1000)\n"
                + "    .attr(\"transform\", function(d) { return \"translate(\" + x(d.x) + \",\" + y(d.y) + \")\"; });\n" + "\n"
                + "  bar.select(\"rect\")\n" + "      .transition()\n" + "      .duration(1000)\n"
                + "      .attr(\"height\", function(d) { return height - y(d.y); })\n"
                + "      .attr(\"fill\", function(d) { return colorScale(d.y) });\n" + "\n" + "  bar.select(\"text\")\n"
                + "      .transition()\n" + "      .duration(1000)\n" + "      .text(function(d) { return formatCount(d.y); });\n" + "\n"
                + "}\n" + "\n" + "// Calling refresh repeatedly.\n" + "setInterval(function() {\n"
                + "  var values = d3.range(1000).map(d3.random.normal(20, 5));\n" + "  refresh(values);\n" + "}, 2000);\n" + "\n"
                + "</script></body></html>";
        return javascriptFile;
    }

}
