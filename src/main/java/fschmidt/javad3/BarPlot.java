package fschmidt.javad3;

/**
 *
 * @author fschmidt
 */
public class BarPlot extends D3Plot{

    private final double[] values;
    private final String[] classes;
    public BarPlot(String[] classes,double[] values){
        super(Version.V4);
        this.classes=classes;
        this.values=values;
    }
    
    @Override
    protected String getJavascriptFile(String... d3jsPaths) {
        String d3jsPath = d3jsPaths[0];
        String dataString = "[";
        for (int i = 0; i < classes.length; i++) {
            if (dataString.length() > 1) {
                dataString += ",";
            }
            dataString += "{\"salesperson\":" + classes[i] + ",\"sales\":" + values[i] + "}";
        }
        dataString += "]";
        String javascriptFile ="<!DOCTYPE html>\n" +
"<meta charset=\"utf-8\">\n" +
"<style> /* set the CSS */\n" +
"\n" +
".bar { fill: steelblue; }\n" +
"\n" +
"</style>\n" +
"<body>\n" +
"	\n" +
"<!-- load the d3.js library -->    	\n" +
"<script src=\""+d3jsPath+"\"></script>\n" +
"<script>\n" +
"\n" +
"// set the dimensions and margins of the graph\n" +
"var margin = {top: 20, right: 20, bottom: 30, left: 40},\n" +
"    width = 960 - margin.left - margin.right,\n" +
"    height = 500 - margin.top - margin.bottom;\n" +
"\n" +
"// set the ranges\n" +
"var x = d3.scaleBand()\n" +
"          .range([0, width])\n" +
"          .padding(0.1);\n" +
"var y = d3.scaleLinear()\n" +
"          .range([height, 0]);\n" +
"          \n" +
"// append the svg object to the body of the page\n" +
"// append a 'group' element to 'svg'\n" +
"// moves the 'group' element to the top left margin\n" +
"var svg = d3.select(\"body\").append(\"svg\")\n" +
"    .attr(\"width\", width + margin.left + margin.right)\n" +
"    .attr(\"height\", height + margin.top + margin.bottom)\n" +
"  .append(\"g\")\n" +
"    .attr(\"transform\", \n" +
"          \"translate(\" + margin.left + \",\" + margin.top + \")\");\n" +
"\n" +
"// get the data\n" +
"var data = "+dataString+"; \n" +
"\n" +
"  // format the data\n" +
"  data.forEach(function(d) {\n" +
"    d.sales = +d.sales;\n" +
"  });\n" +
"\n" +
"  // Scale the range of the data in the domains\n" +
"  x.domain(data.map(function(d) { return d.salesperson; }));\n" +
"  y.domain([0, d3.max(data, function(d) { return d.sales; })]);\n" +
"\n" +
"  // append the rectangles for the bar chart\n" +
"  svg.selectAll(\".bar\")\n" +
"      .data(data)\n" +
"    .enter().append(\"rect\")\n" +
"      .attr(\"class\", \"bar\")\n" +
"      .attr(\"x\", function(d) { return x(d.salesperson); })\n" +
"      .attr(\"width\", x.bandwidth())\n" +
"      .attr(\"y\", function(d) { return y(d.sales); })\n" +
"      .attr(\"height\", function(d) { return height - y(d.sales); });\n" +
"\n" +
"  // add the x Axis\n" +
"  svg.append(\"g\")\n" +
"      .attr(\"transform\", \"translate(0,\" + height + \")\")\n" +
"      .call(d3.axisBottom(x));\n" +
"\n" +
"  // add the y Axis\n" +
"  svg.append(\"g\")\n" +
"      .call(d3.axisLeft(y));\n" +
"\n" +
"\n" +
"</script>\n" +
"</body>";
        return javascriptFile;
    }
    
}
