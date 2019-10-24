package test;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import test.ChartRunnerClusteringTest;
import test.JsonConvertTest;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5)
@Measurement(iterations = 30)
public class NetworkDBempty {
	
	static HttpClient client = HttpClient.newHttpClient();
	public static List<String> list = Arrays.asList(
			"tenCluster.csv",
			"twentyCluster.csv", 
			"thirtyCluster.csv", 
			"fourtyCluster.csv",
			"fiftyCluster.csv", 
			"sixyCluster.csv", 
			"seventyCluster.csv",
			"eightyCluster.csv", 
			"ninetyCluster.csv", 
			"hundretCluster.csv",
			"noCluster.csv"
			);
	
	public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(NetworkDBempty.class.getSimpleName())
                .forks(1)
                .result("networkRequestResult.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opt).run();
    }
	
	public static int sendChartToServer() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8000/chart"))
				.header("Content-Type", "application/json; utf-8")
				.POST(BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		if (response.statusCode() != 200) {
			return -1;
		}
		return Integer.parseInt(response.headers().firstValue("location").orElse("-1"));
	}
	
	public static boolean sendData(int chartId, String data) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(String.format("http://localhost:8000/chart/%d", chartId)))
					.header("Content-Type", "application/json")
					.POST(BodyPublishers.ofString(data))
					.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
						
			return response.statusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<String> setupJsons() {
		ArrayList<String> jsons = new ArrayList<String>();
		String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\points.csv";
		String cluster;
		for(int i = 0; i < list.size(); i++) {
			cluster  = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(i);
			jsons.add(
					JsonConvertTest.addData(
						ChartRunnerClusteringTest.readPoints(datapoints), 
						ChartRunnerClusteringTest.readCluster(cluster)
					)
				);
		}
		return jsons;
	}
	
	@State(Scope.Benchmark)
	public static class MyState {
		
		static ArrayList<String> jsons = setupJsons();
		
		
		@Setup(Level.Iteration)
		public static void deleteDB() throws IOException, InterruptedException {
			HttpRequest delete = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8000/chart"))
					.DELETE()
					.build();
			HttpResponse<String> response = client.send(delete, BodyHandlers.ofString());
			System.out.println(response);
		}
	}
   
	@Benchmark
	public static void request10(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(0));
	}
	@Benchmark
	public static void request20(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(1));
	}
	@Benchmark
	public static void request30(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(2));
	}
	@Benchmark
	public static void request40(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(3));
	}
	@Benchmark
	public static void request50(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(4));
	}
	@Benchmark
	public static void request60(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(5));
	}
	@Benchmark
	public static void request70(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(6));
	}
	@Benchmark
	public static void request80(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(7));
	}
	@Benchmark
	public static void request90(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(8));
	}
	@Benchmark
	public static void request100(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(9));
	}
	@Benchmark
	public static void request0(MyState state) throws IOException, InterruptedException {
		int id = sendChartToServer();
		sendData(id, state.jsons.get(10));
	}
}