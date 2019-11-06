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
import org.openjdk.jmh.infra.Blackhole;
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
public class ClientAll{	
	
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
                .include(ClientAll.class.getSimpleName())
                .forks(1)
                .result("test.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opt).run();
    }
	
	@State(Scope.Benchmark)
	public static class MyState {
		
		static String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\points.csv";
		
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
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(0);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request20(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(1);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request30(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(2);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request40(MyState state, Blackhole blackhole) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(3);
		blackhole.consume(cluster);;
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		blackhole.consume(json);
		int id = NetworkDBempty.sendChartToServer();
		blackhole.consume(id);
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request50(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(4);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request60(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(5);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request70(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(6);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request80(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(7);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request90(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(8);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request100(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(9);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
	@Benchmark
	public static void request0(MyState state) throws IOException, InterruptedException {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(10);
		String json = JsonConvertTest.addData(
				ChartRunnerClusteringTest.readPoints(state.datapoints), 
				ChartRunnerClusteringTest.readCluster(cluster)
			);
		int id = NetworkDBempty.sendChartToServer();
		NetworkDBempty.sendData(id, json);
	}
}