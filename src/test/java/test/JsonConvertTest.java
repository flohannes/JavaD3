package test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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

import javad3.d3objects.ClusteringVideo.ClusteringData;
import test.ChartRunnerClusteringTest;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5)
@Measurement(iterations = 30)
public class JsonConvertTest {
	
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
                .include(JsonConvertTest.class.getSimpleName())
                .forks(1)
                .result("jsonCreationResult.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opt).run();
    }
	
	private final static String OPTION_DATA_KEY = "data";
	private final static String OPTION_POINTS_KEY = "points";
	private final static String OPTION_CLUSTER_KEY = "cluster";
	private final static String OPTION_DATA_ENTRY_X = "x";
	private final static String OPTION_DATA_ENTRY_Y = "y";
	private final static String OPTION_DATA_ENTRY_RADIUS = "radius";
	private final static String OPTION_DATA_ENTRY_TIMESTAMP = "timeStamp";
	
	public static String addData(List<ClusteringData> data, HashMap<String, ArrayList<ClusteringData>> cluster_map) {
		JsonArrayBuilder jsonPointsArray = Json.createArrayBuilder();
		JsonObjectBuilder jsonOverallCluster = Json.createObjectBuilder();
		JsonArrayBuilder jsonClusterArray = Json.createArrayBuilder();
			
		/*
		 * write the datapoints into a json object
		 */
		for(ClusteringData entry : data) {
			jsonPointsArray = jsonPointsArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
		}
		
		/*
		 * write the cluster in a json object
		 */
		Iterator<Entry<String, ArrayList<ClusteringData>>> it = cluster_map.entrySet().iterator();
		while(it.hasNext()) {
			
            HashMap.Entry pair = (HashMap.Entry)it.next();
            ArrayList<ClusteringData> cluster = (ArrayList<ClusteringData>) pair.getValue();
            
            /*
             * add every element for one timestamp into an json array
             */
			for(ClusteringData entry : cluster) {
				jsonClusterArray = jsonClusterArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
			}

			/*
			 * write the json array to the timestamp key into an json object
			 */
			jsonOverallCluster = jsonOverallCluster.add((String) pair.getKey(), jsonClusterArray);
			jsonClusterArray = Json.createArrayBuilder();
		}
		
		/*
		 * concat datapoints and cluster into one json object
		 */
		JsonObject concat_data = Json.createObjectBuilder().add(OPTION_POINTS_KEY, jsonPointsArray).add(OPTION_CLUSTER_KEY, jsonOverallCluster).build();
		String json = Json.createObjectBuilder().add(OPTION_DATA_KEY, concat_data).build().toString();
		return json;
	}
	
	@State(Scope.Benchmark)
	public static class MyState {
		static String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\points.csv";
		static ArrayList<ClusteringData> all_datapoints = ChartRunnerClusteringTest.readPoints(datapoints);

		static ArrayList<HashMap<String, ArrayList<ClusteringData>>> cluster_map_list = new ArrayList<HashMap<String, ArrayList<ClusteringData>>>();
		static String cluster;
		
		@Setup(Level.Trial)
		public static void setup() {
			System.out.println("SETUP");
			for(int i = 0; i < list.size(); i++) {
				cluster  = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\"+list.get(i);
				cluster_map_list.add(ChartRunnerClusteringTest.readCluster(cluster));
			}
		}	
	}
   
	@Benchmark
	public static void noCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(10));
	}
	
	@Benchmark
	public static void tenCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(0));
	}
	
	@Benchmark
	public static void twentyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(1));
	
	}
	
	@Benchmark
	public static void thirtyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(2));
	}
	
	@Benchmark
	public static void fourtyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(3));
	}
	@Benchmark
	public static void fiftyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(4));
	}
	@Benchmark
	public static void sixtyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(5));
	}
	@Benchmark
	public static void seventyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(6));
	}
	@Benchmark
	public static void eightyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(7));
	}
	@Benchmark
	public static void ninetyCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(8));
	}
	@Benchmark
	public static void hundretCluster(MyState state) {
		addData(state.all_datapoints, state.cluster_map_list.get(9));
	}
}