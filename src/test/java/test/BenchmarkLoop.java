package test;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import test.ChartRunnerClusteringTest;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5)
@Measurement(iterations = 30)
public class BenchmarkLoop {
	
	static String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\points.csv";
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoop.class.getSimpleName())
                .forks(1)
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opt).run();
        
    }
    @Benchmark
	public static void noCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\noCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void tenCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\tenCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	
	@Benchmark
	public static void twentyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\twentyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	
	@Benchmark
	public static void thirtyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\thirtyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void fourtyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\fourtyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}	
	@Benchmark
	public static void fiftyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\fiftyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void sixtyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\sixyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void seventyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\seventyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void eightyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\eightyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void ninetyCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\ninetyCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}
	@Benchmark
	public static void hundretCluster() {
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\JavaD3\\testData\\hundretCluster.csv";
		ChartRunnerClusteringTest.readData(datapoints, cluster);
	}

}