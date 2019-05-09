package javad3.renderer.node;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javad3.d3objects.*;

public class NodeRenderer {

	private static final String OPTION_EJS_SCRIPT = "ejs";

	private String serverHost;
	private int serverPort;
	private boolean debug = false;

	public NodeRenderer(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}

	public boolean isDebugMode() {
		return this.debug;
	}

	public Histogram createHistogramChart() {
		int chartId = this.createChart(HistogramImpl.CHART_TYPE);

		return new HistogramImpl(chartId, this);
	}

	public BarChart createBarChart() {
		int chartId = this.createChart(BarChartImpl.CHART_TYPE);

		return new BarChartImpl(chartId, this);
	}

	public TimeSeries createTimeSeries() {
		int chartId = this.createChart(TimeSeriesImpl.CHART_TYPE);

		return new TimeSeriesImpl(chartId, this);
	}
	
	String getURLForChartId(int chartId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(serverHost).append(":").append(serverPort).append("/").append(chartId);

		return sb.toString();
	}

	private int createChart(String chartType) {
		try {
			return  this.sendChartToServer(chartType);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private int sendChartToServer(String chartType) throws IOException {
		HttpURLConnection conn = this.createRequestForNewChart(chartType);

		if (conn.getResponseCode() != 201) {
			if (this.debug) {
				System.out.println(String.format("Chart not added. Error code %d", conn.getResponseCode()));
			}

			return -1;
		}

		if (this.debug) {
			System.out.println(String.format("Chart added of type %s", chartType));
		}

		return Integer.parseInt(conn.getHeaderField("Location"));
	}

	private HttpURLConnection createRequestForNewChart(String chartType) throws IOException {
		URL url = new URL(String.format("http://%s:%d/", this.serverHost, this.serverPort));

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty(OPTION_EJS_SCRIPT, chartType);

		return conn;
	}

	String getHtml(int chartId) {
		try {
			return this.getHtmlFromServer(chartId);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	private String getHtmlFromServer(int chartId) throws IOException {
		HttpURLConnection conn = createRequestToRetrieveChart(chartId);

		if (conn.getResponseCode() != 200) {
			return null;
		}

		return this.parseContentFromResponse(conn);
	}

	private HttpURLConnection createRequestToRetrieveChart(int chartId) throws IOException {
		URL url = new URL(String.format("http://%s:%d/%d", serverHost, serverPort, chartId));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		return conn;
	}

	private String parseContentFromResponse(HttpURLConnection conn) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		InputStream is = conn.getInputStream();

		try (Scanner scanner = new Scanner(is);) {
			while (scanner.hasNext()) {
				stringBuilder.append(scanner.nextLine() + (scanner.hasNext() ? "\n" : ""));
			}

			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	boolean setOption(int chartId, String option, String value) {
		return setOptions(chartId, new String[] { option }, new String[] { value });
	}

	boolean setOptions(int chartId, String[] options, String[] values) {
		if (options.length != values.length) {
			return false;
		}

		try {
			HttpURLConnection conn = createRequestToSendOptions(chartId, options, values);
			
			return conn.getResponseCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private HttpURLConnection createRequestToSendOptions(int chartId, String[] options, String[] values) throws IOException {
		URL url = new URL(String.format("http://%s:%d/%d", serverHost, serverPort, chartId));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		for (int i = 0; i < options.length; i++) {
			conn.setRequestProperty(options[i], values[i]);
		}

		return conn;
	}
}