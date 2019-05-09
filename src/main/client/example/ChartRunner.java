package example;

import java.util.Random;

import javad3.d3objects.*;
import javad3.renderer.node.LocalNodeServer;
import javad3.renderer.node.NodeRenderer;

public class ChartRunner {

	public static void main(String[] args) {
		// server runs on port 8000 and debug messages are turned on
		LocalNodeServer.setDebugMode(true);
		LocalNodeServer.start(8000);

		NodeRenderer nodeRenderer = new NodeRenderer("localhost", 8000);
		nodeRenderer.setDebugMode(true);

		/*
		 * You can find your charts at:
		 * 
		 * http://localhost:8000/<chartID>
		 * 
		 * 
		 */

		// try different chart types
		// Histogram histogram = nodeRenderer.createHistogramChart();
		// BarChart c = nodeRenderer.createBarChart();
		TimeSeries c = nodeRenderer.createTimeSeries();

		System.out.println("You can find your chart at: " + c.getLocation());

		// set chart parameters
		c.setTitle("Mein Test Titel");
		c.setHeight(500);
		c.setWidth(500);

		// generate random data
		Random r = new Random();
		for (int i = 0; i < 20; i++) {

			// add data for histogram
			// c.addData((double) r.nextInt() % 200);

			// add data for barchart or timeseries
			c.addData(Integer.toString(i), (double) Math.abs(r.nextInt() % 100));
		}

		// get html from chart
		// System.out.println(c.getHtml());
	}
}
