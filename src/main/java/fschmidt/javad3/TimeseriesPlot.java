package fschmidt.javad3;

import java.util.Date;

/**
 *
 * @author fschmidt
 */
public class TimeseriesPlot extends D3Plot {

    private final long[] times;
    private final double[] values;

    public TimeseriesPlot(double[] values, Date[] date) {
        super(Version.V3);
        this.values = values;
        times = new long[date.length];
        for (int i = 0; i < date.length; i++) {
            times[i] = date[i].getTime();
        }
    }

    @Override
    protected String getJavascriptFile(String... d3jsPaths) {
        String d3jsPath = d3jsPaths[0];
        String dataString = "[";
        for (int i = 0; i < times.length; i++) {
            if (dataString.length() > 1) {
                dataString += ",";
            }
            dataString += "{\"date\":" + times[i] + ",\"close\":" + values[i] + "}";
        }
        dataString += "]";
        String javascruptFile = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n"
                + "<script type=\"text/javascript\" src=\""+d3jsPath+"\"></script>\n" + "<style>\n"
                + "body{ font: Arial 12px; text-align: center;}\n" + ".axis path, .axis line{\n" + "fill: none;\n" + "stroke: black;\n"
                + "stroke-width: 1;\n" + "shape-rendering: crispEdges;\n" + "}\n" + "</style>\n"
                + "</head>\n" + "<body>\n" 
                + "<script type=\"text/javascript\">\n" + "//Set margins and sizes\n" + "var margin = {\n" + "top: 20,\n" + "bottom: 50,\n"
                + "right: 30,\n" + "left: 50\n" + "};\n" + "\n" + "var width = 960 - margin.left - margin.right;\n"
                + "var height = 500 - margin.top - margin.bottom;\n" + "//Create date parser\n"
                + "var ParseDate = d3.time.format(\"%Q\").parse;\n" + "//Create x and y scale to scale inputs\n"
                + "var xScale = d3.time.scale().range([0, width]);\n" + "var yScale = d3.scale.linear().range([height, 0]);\n" + "\n"
                + "//Create x and y axes\n" + "var xAxis = d3.svg.axis().scale(xScale)\n" + ".orient(\"bottom\")\n" + ".ticks(5);\n" + "\n"
                + "var yAxis = d3.svg.axis().scale(yScale)\n" + ".orient(\"left\");\n" + "\n" + "//Create a area generator\n"
                + "var area = d3.svg.area()\n" + ".x(function(d){\n" + "return xScale(d.date);\n" + "})\n" + ".y1(function(d){\n"
                + "return yScale(d.close);\n" + "});\n" + "\n" + "//Create an SVG element and append it to the DOM\n"
                + "var svgElement = d3.select(\"body\")\n"
                + ".append(\"svg\").attr({\"width\": width+margin.left+margin.right, \"height\": height+margin.top+margin.bottom})\n"
                + ".append(\"g\")\n" + ".attr(\"transform\",\"translate(\"+margin.left+\",\"+margin.top+\")\");\n" + "\n"
                + "var data = "+dataString+";\n" + "\n"
                + "  data.forEach(function(d,i) {     \n" + "    d.date = d.date;\n" + "    d.close = +d.close;    \n"
                + "  });\n" + "\n" + "//Set the domains of our scales\n"
                + "xScale.domain(d3.extent(data, function(d){ return d.date; }));\n"
                + "yScale.domain([0, d3.max(data, function(d){ return d.close; })]);\n" + "area.y0(yScale(0));\n"
                + "//append the svg path\n" + "var path = svgElement.append(\"path\")\n" + ".attr(\"d\", area(data))\n"
                + ".attr(\"fill\", \"steelblue\");\n" + "//Add X Axis\n" + "var x = svgElement.append(\"g\")\n"
                + ".attr(\"transform\", \"translate(0,\"+height+\")\")\n" + ".attr(\"class\", \"x axis\")\n" + ".call(xAxis);\n" + "\n"
                + "//Add Y Axis\n" + "var y = svgElement.append(\"g\")\n" + ".call(yAxis)\n" + ".attr(\"class\", \"y axis\");\n" + "\n"
                + "//Add label to Y axis\n" + "y.append(\"text\")\n" + ".attr(\"fill\", \"#000\")\n"
                + ".attr(\"transform\", \"rotate(-90)\")\n" + ".attr(\"y\", 6)\n" + ".attr(\"dy\", \"0.71em\")\n"
                + ".attr(\"text-anchor\", \"end\")\n" + ".text(\"value\");\n"  + "</script>\n" + "</body>\n" + "</html>";
        return javascruptFile;
    }

}
