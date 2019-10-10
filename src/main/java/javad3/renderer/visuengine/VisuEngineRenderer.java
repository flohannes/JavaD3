package javad3.renderer.visuengine;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import javax.json.Json;

import javad3.d3objects.*;
import javad3.renderer.Renderer;

public class VisuEngineRenderer implements Renderer {

	private String serverHost;
	private int serverPort;
	private boolean debug = false;
	private HttpClient httpClient;

	public VisuEngineRenderer(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.httpClient = HttpClient.newHttpClient();
	}
	
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}

	public boolean isDebugMode() {
		return this.debug;
	}

	public TimeSeries createTimeSeries() {
		int chartId = this.createChart();

		return new TimeSeriesImpl(chartId, this);
	}
	
	public ClusteringVideo createClusteringVideo() {
		int chartId = this.createChart();
		
		return new ClusteringVideoImpl(chartId, this);
	}
	
	String getURLForChartIdUnitAndTemplate(int chartId, String unit, String template) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(serverHost).append(":").append(serverPort)
			.append("/chart/").append(chartId)
			.append("?unit=").append(unit)
			.append("&template=").append(template);

		return sb.toString();
	}

	private int createChart() {
		try {
			return this.sendChartToServer();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private int sendChartToServer() throws IOException, InterruptedException {
		HttpRequest request = this.createRequestForNewChart();
		HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());
		
		if (response.statusCode() != 200) {
			if (this.debug) {
				System.out.println(String.format("Chart not added. Error code %d", response.statusCode()));
			}

			return -1;
		}

		if (this.debug) {
			System.out.println("Chart added");
		}

		return Integer.parseInt(response.headers().firstValue("location").orElse("-1"));
	}

	private HttpRequest createRequestForNewChart() throws IOException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://%s:%d/chart", this.serverHost, this.serverPort)))
				.header("Content-Type", "application/json; utf-8")
				.POST(BodyPublishers.noBody())
				.build();

		return request;
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
		URL url = new URL(String.format("http://%s:%d/chart/%d", serverHost, serverPort, chartId));
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
		String body = Json.createObjectBuilder().add(option, value).build().toString();
		return sendData(chartId, body);
	}

	boolean sendData(int chartId, String data) {
		try {
			HttpRequest request = createRequestToSendData(chartId, data);
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			return response.statusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private HttpRequest createRequestToSendData(int chartId, String body) throws IOException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(String.format("http://%s:%d/chart/%d", serverHost, serverPort, chartId)))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(body))
				.build();

		return request;
	}
}
