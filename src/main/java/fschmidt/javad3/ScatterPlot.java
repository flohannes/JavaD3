package fschmidt.javad3;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;
import javafx.scene.web.WebEngine;

/**
 *
 * @author fschmidt
 */
public class ScatterPlot {

    private final double[] x;
    private final double[] y;

    public ScatterPlot(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public void saveFile(String path) throws FileNotFoundException, UnsupportedEncodingException, MalformedURLException, IOException {
        //Create D3js file
        PrintWriter writer = new PrintWriter(path + ".html", "UTF-8");
        writer.print(getJavascriptFile());
        writer.close();
        //Run js through node
        Desktop.getDesktop().browse(new File(path + ".html").toURI());
        WebEngine webengine = new WebEngine(path);
        webengine.load(new File(path + ".html").toURI().toURL().toExternalForm());
//        Document document = webengine.getDocument();
//        document.getElementById(path)
    }

    private String getJavascriptFile() {
        System.out.println(Arrays.toString(x));
        return "<!DOCTYPE html>\n" + "<html>\n" + "  <head>\n" + "    <title>The d3 test</title>\n"
                + "    <script type='text/javascript' src='http://mbostock.github.com/d3/d3.v2.js' charset=\"utf-8\"></script>\n"
                +"  <script src=\"https://cdn.rawgit.com/eligrey/canvas-toBlob.js/f1a01896135ab378aa5c0118eadd81da55e698d8/canvas-toBlob.js\"></script>\n" +
"	 <script src=\"https://cdn.rawgit.com/eligrey/FileSaver.js/e9d941381475b5df8b7d7691013401e171014e89/FileSaver.min.js\"></script>"
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
                + "<script>"
                + "window.onload = thing();"
                + "function thing(){\n" + "	var svgString = getSVGString(chart.node());\n"
                + "	svgString2Image( svgString, 2*width, 2*height, 'png', save ); // passes Blob and filesize String to the callback\n"
                + "	function save( dataBlob, filesize ){\n"
                + "		saveAs( dataBlob, 'D3 vis exported to PNG.png' ); // FileSaver.js function\n" + "	}\n" + "}"
                + "function getSVGString( svgNode ) {\n" + "	svgNode.setAttribute('xlink', 'http://www.w3.org/1999/xlink');\n"
                + "	var cssStyleText = getCSSStyles( svgNode );\n" + "	appendCSS( cssStyleText, svgNode );\n"
                + "	var serializer = new XMLSerializer();\n" + "	var svgString = serializer.serializeToString(svgNode);\n"
                + "	svgString = svgString.replace(/(\\w+)?:?xlink=/g, 'xmlns:xlink='); // Fix root xlink without namespace\n"
                + "	svgString = svgString.replace(/NS\\d+:href/g, 'xlink:href'); // Safari NS namespace fix\n" + "	return svgString;\n"
                + "	function getCSSStyles( parentElement ) {\n" + "		var selectorTextArr = [];\n"
                + "		// Add Parent element Id and Classes to the list\n" + "		selectorTextArr.push( '#'+parentElement.id );\n"
                + "		for (var c = 0; c < parentElement.classList.length; c++)\n"
                + "				if ( !contains('.'+parentElement.classList[c], selectorTextArr) )\n"
                + "					selectorTextArr.push( '.'+parentElement.classList[c] );\n"
                + "		// Add Children element Ids and Classes to the list\n" + "		var nodes = parentElement.getElementsByTagName(\"*\");\n"
                + "		for (var i = 0; i < nodes.length; i++) {\n" + "			var id = nodes[i].id;\n"
                + "			if ( !contains('#'+id, selectorTextArr) )\n" + "				selectorTextArr.push( '#'+id );\n"
                + "			var classes = nodes[i].classList;\n" + "			for (var c = 0; c < classes.length; c++)\n"
                + "				if ( !contains('.'+classes[c], selectorTextArr) )\n" + "					selectorTextArr.push( '.'+classes[c] );\n" + "		}\n"
                + "		// Extract CSS Rules\n" + "		var extractedCSSText = \"\";\n"
                + "		for (var i = 0; i < document.styleSheets.length; i++) {\n" + "			var s = document.styleSheets[i];\n" + "			\n"
                + "			try {\n" + "			    if(!s.cssRules) continue;\n" + "			} catch( e ) {\n"
                + "		    		if(e.name !== 'SecurityError') throw e; // for Firefox\n" + "		    		continue;\n" + "		    	}\n"
                + "			var cssRules = s.cssRules;\n" + "			for (var r = 0; r < cssRules.length; r++) {\n"
                + "				if ( contains( cssRules[r].selectorText, selectorTextArr ) )\n" + "					extractedCSSText += cssRules[r].cssText;\n"
                + "			}\n" + "		}\n" + "		\n" + "		return extractedCSSText;\n" + "		function contains(str,arr) {\n"
                + "			return arr.indexOf( str ) === -1 ? false : true;\n" + "		}\n" + "	}\n" + "	function appendCSS( cssText, element ) {\n"
                + "		var styleElement = document.createElement(\"style\");\n" + "		styleElement.setAttribute(\"type\",\"text/css\"); \n"
                + "		styleElement.innerHTML = cssText;\n" + "		var refNode = element.hasChildNodes() ? element.children[0] : null;\n"
                + "		element.insertBefore( styleElement, refNode );\n" + "	}\n" + "}\n"
                + "function svgString2Image( svgString, width, height, format, callback ) {\n" + "	var format = format ? format : 'png';\n"
                + "	var imgsrc = 'data:image/svg+xml;base64,'+ btoa( unescape( encodeURIComponent( svgString ) ) ); // Convert SVG string to data URL\n"
                + "	var canvas = document.createElement(\"canvas\");\n" + "	var context = canvas.getContext(\"2d\");\n"
                + "	canvas.width = width;\n" + "	canvas.height = height;\n" + "	var image = new Image();\n"
                + "	image.onload = function() {\n" + "		context.clearRect ( 0, 0, width, height );\n"
                + "		context.drawImage(image, 0, 0, width, height);\n" + "		canvas.toBlob( function(blob) {\n"
                + "			var filesize = Math.round( blob.length/1024 ) + ' KB';\n" + "			if ( callback ) callback( blob, filesize );\n"
                + "		});\n" + "		\n" + "	};\n" + "	image.src = imgsrc;\n" + "}"
                + "</script>"
                + "  </body>\n" + "</html>";
    }
}
