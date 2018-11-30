package fschmidt.javad3;

import java.util.Arrays;

/**
 *
 * @author fschmidt
 */
public class ScatterPlot extends D3Plot {

    private final double[] x;
    private final double[] y;

    public ScatterPlot(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected String getJavascriptFile(String d3jsPath) {
        return "<!DOCTYPE html>\n" + "<html>\n" + "  <head>\n" + "    <title>The d3 test</title>\n"
                + "<style>\n" + ".chart {\n" + "\n" + "}\n" + "\n" + ".main text {\n" + "    font: 10px sans-serif;	\n" + "}\n" + "\n"
                + ".axis line, .axis path {\n" + "    shape-rendering: crispEdges;\n" + "    stroke: black;\n" + "    fill: none;\n" + "}\n"
                + "\n" + "circle {\n" + "    fill: steelblue;\n" + "}\n" + "\n" + "</style>"
                + "    <script type='text/javascript' src='" + d3jsPath + "' charset=\"utf-8\"></script>\n"
                + "  </head>\n" + "  <body>\n" + "    <div class='content'>\n" + "      <!-- /the chart goes here -->\n" + "    </div>\n"
                + "    <script>\n"
                + "    var xdata = " + Arrays.toString(x) + ",\n"
                + "    ydata = " + Arrays.toString(y) + ";\n" + "\n"
                + "// size and margins for the chart\n" + "var margin = {top: 20, right: 15, bottom: 60, left: 60}\n"
                + "  , width = 960 - margin.left - margin.right\n" + "  , height = 500 - margin.top - margin.bottom;\n" + "\n"
                + "// x and y scales, I've used linear here but there are other options\n"
                + "// the scales translate data values to pixel values for you\n" + "var x = d3.scale.linear()\n"
                + "          .domain([0, d3.max(xdata)])  // the range of the values to plot\n"
                + "          .range([ 0, width ]);        // the pixel range of the x-axis\n" + "\n" + "var y = d3.scale.linear()\n"
                + "          .domain([0, d3.max(ydata)])\n" + "          .range([ height, 0 ]);\n" + "\n"
                + "// the chart object, includes all margins\n" + "var chart = d3.select('body')\n" + ".append('svg:svg')\n"
                + ".attr('width', width + margin.right + margin.left)\n" + ".attr('height', height + margin.top + margin.bottom)\n"
                + ".attr('class', 'chart')\n" + "\n" + "// the main object where the chart and axis will be drawn\n"
                + "var main = chart.append('g')\n" + ".attr('transform', 'translate(' + margin.left + ',' + margin.top + ')')\n"
                + ".attr('width', width)\n" + ".attr('height', height)\n" + ".attr('class', 'main')   \n" + "\n" + "// draw the x axis\n"
                + "var xAxis = d3.svg.axis()\n" + ".scale(x)\n" + ".orient('bottom');\n" + "main.append('g')\n"
                + ".attr('transform', 'translate(0,' + height + ')')\n" + ".attr('class', 'main axis date')\n" + ".call(xAxis);\n" + "\n"
                + "// draw the y axis\n" + "var yAxis = d3.svg.axis()\n" + ".scale(y)\n" + ".orient('left');\n" + "\n"
                + "main.append('g')\n" + ".attr('transform', 'translate(0,0)')\n" + ".attr('class', 'main axis date')\n" + ".call(yAxis);\n"
                + "\n" + "// draw the graph object\n" + "var g = main.append(\"svg:g\"); \n" + "\n" + "g.selectAll(\"scatter-dots\")\n"
                + "  .data(ydata)  // using the values in the ydata array\n"
                + "  .enter().append(\"svg:circle\")  // create a new circle for each value\n"
                + "      .attr(\"cy\", function (d) { return y(d); } ) // translate y value to a pixel\n"
                + "      .attr(\"cx\", function (d,i) { return x(xdata[i]); } ) // translate x value\n"
                + "      .attr(\"r\", 10) // radius of circle\n" + "      .style(\"opacity\", 0.6); // opacity of circle\n"
                + ""
                + "    </script>\n"
                + "  </body>\n" + "</html>";
    }
}
