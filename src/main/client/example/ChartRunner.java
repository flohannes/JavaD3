package example;

import java.util.Random;

import javad3.d3objects.*;
import javad3.renderer.node.LocalNodeServer;
import javad3.renderer.node.NodeRenderer;

public class ChartRunner {

	public static void main(String[] args) throws InterruptedException {
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
		TimeSeries timeseries = nodeRenderer.createTimeSeries();

		System.out.println("You can find your chart at: " + timeseries.getLocation());

		// set chart parameters
		timeseries.setTitle("Mein Test Titel");
		timeseries.setHeight(900);
		timeseries.setWidth(1600);
		timeseries.setDataRefreshInterval(500);
		timeseries.setVisibleDatapointsLimit(30);

		// generate random data
		Random r = new Random();
		for (int i = 0; i < 20; i++) {

			// add data for histogram
			// histogram.addData((double) r.nextInt() % 200);

			// add data for barchart or timeseries
			timeseries.addData(Integer.toString(i), (double) Math.abs(r.nextInt() % 100));
		}
		
		// keep adding data
		int i = 20;
		while (true) {
			Thread.sleep(500);
			timeseries.addData(Integer.toString(i), (double) Math.abs(r.nextInt() % 100));
			i++;
		}

		// get html from chart
		// System.out.println(c.getHtml());
	}
}
