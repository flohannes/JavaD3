package fschmidt.javad3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fschmidt
 */
public class MultiTimeseriesPlot extends D3Plot {

    private final long[] times;
    private final List<double[]> values;
    private final String[] dimNames;

    public MultiTimeseriesPlot(Date[] date, String[] dimNames, double[]... values) {
        super(Version.V4);
        this.values = new ArrayList<>();
        this.values.addAll(Arrays.asList(values));
        this.dimNames = dimNames;
        times = new long[date.length];
        for (int i = 0; i < date.length; i++) {
            times[i] = date[i].getTime();
        }
    }

    @Override
    protected String getJavascriptFile(String... d3jsPaths) {
        String d3jsPath = d3jsPaths[0];
        String dataString = "[";
        for (int j = 0; j < dimNames.length; j++) {
            for (int i = 0; i < times.length; i++) {
                if (dataString.length() > 1) {
                    dataString += ",";
                }
                dataString += "{\"date\":" + times[i]+",\"symbol\":\"" + dimNames[j] + "\",\"price\":" + values.get(j)[i]+"}";
            }
        }
        dataString += "]";
        String javascruptFile = "<!DOCTYPE html>\n" + "<meta charset=\"utf-8\">\n" + "<style> /* set the CSS */\n" + "\n"
                + "body { font: 12px Arial;}\n" + "\n" + "path { \n" + "    stroke: steelblue;\n" + "    stroke-width: 2;\n"
                + "    fill: none;\n" + "}\n" + "\n" + ".axis path,\n" + ".axis line {\n" + "    fill: none;\n" + "    stroke: grey;\n"
                + "    stroke-width: 1;\n" + "    shape-rendering: crispEdges;\n" + "}\n" + "\n" + ".legend {\n" + "    font-size: 16px;\n"
                + "    font-weight: bold;\n" + "    text-anchor: middle;\n" + "}\n" + "\n" + "</style>\n" + "<body>\n" + "\n"
                + "<!-- load the d3.js library -->    \n" + "<script src=\"" + d3jsPath + "\"></script>\n" + "\n" + "<script>\n" + "\n"
                + "// Set the dimensions of the canvas / graph\n" + "var margin = {top: 30, right: 20, bottom: 70, left: 50},\n"
                + "    width = 800 - margin.left - margin.right,\n" + "    height = 600 - margin.top - margin.bottom;\n" + "\n"
                + "// Parse the date / time\n" + "var parseDate = d3.timeParse(\"%Q\");\n" + "\n" + "// Set the ranges\n"
                + "var x = d3.scaleTime().range([0, width]);  \n" + "var y = d3.scaleLinear().range([height, 0]);\n" + "\n"
                + "// Define the line\n" + "var priceline = d3.line()	\n" + "    .x(function(d) { return x(d.date); })\n"
                + "    .y(function(d) { return y(d.price); });\n" + "    \n" + "// Adds the svg canvas\n"
                + "var svg = d3.select(\"body\")\n" + "    .append(\"svg\")\n"
                + "        .attr(\"width\", width + margin.left + margin.right)\n"
                + "        .attr(\"height\", height + margin.top + margin.bottom)\n" + "    .append(\"g\")\n"
                + "        .attr(\"transform\", \n" + "              \"translate(\" + margin.left + \",\" + margin.top + \")\");\n" + "\n"
                + "// Get the data\n" + "var data ="+dataString+";\n" + "    data.forEach(function(d) {\n" + "		d.date = parseDate(d.date);\n"
                + "		d.price = +d.price;\n" + "    });\n" + "\n" + "    // Scale the range of the data\n"
                + "    x.domain(d3.extent(data, function(d) { return d.date; }));\n"
                + "    y.domain([0, d3.max(data, function(d) { return d.price; })]);\n" + "\n" + "    // Nest the entries by symbol\n"
                + "    var dataNest = d3.nest()\n" + "        .key(function(d) {return d.symbol;})\n" + "        .entries(data);\n" + "\n"
                + "    // set the colour scale\n" + "    var color = d3.scaleOrdinal(d3.schemeCategory10);\n" + "\n"
                + "    legendSpace = width/dataNest.length; // spacing for the legend\n" + "\n" + "    // Loop through each symbol / key\n"
                + "    dataNest.forEach(function(d,i) { \n" + "\n" + "        svg.append(\"path\")\n"
                + "            .attr(\"class\", \"line\")\n"
                + "            .style(\"stroke\", function() { // Add the colours dynamically\n"
                + "                return d.color = color(d.key); })\n" + "            .attr(\"d\", priceline(d.values));\n" + "\n"
                + "        // Add the Legend\n" + "        svg.append(\"text\")\n"
                + "            .attr(\"x\", (legendSpace/2)+i*legendSpace)  // space legend\n"
                + "            .attr(\"y\", height + (margin.bottom/2)+ 5)\n"
                + "            .attr(\"class\", \"legend\")    // style the legend\n"
                + "            .style(\"fill\", function() { // Add the colours dynamically\n"
                + "                return d.color = color(d.key); })\n" + "            .text(d.key); \n" + "\n" + "    });\n" + "\n"
                + "  // Add the X Axis\n" + "  svg.append(\"g\")\n" + "      .attr(\"class\", \"axis\")\n"
                + "      .attr(\"transform\", \"translate(0,\" + height + \")\")\n" + "      .call(d3.axisBottom(x));\n" + "\n"
                + "  // Add the Y Axis\n" + "  svg.append(\"g\")\n" + "      .attr(\"class\", \"axis\")\n"
                + "      .call(d3.axisLeft(y));\n" + "</script>\n" + "</body>";
        return javascruptFile;
    }

}
