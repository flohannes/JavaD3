package example;

import javad3.d3objects.*;
import javad3.d3objects.TimeSeries.TimeSeriesData;
import javad3.renderer.visuengine.VisuEngineRenderer;
import javad3.video.VideoCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class ChartRunner {

	public static void main(String[] args) throws InterruptedException {
		VisuEngineRenderer renderer = new VisuEngineRenderer("localhost", 8000);
		
		TimeSeries timeseries = renderer.createTimeSeries();

		System.out.println("You can find your chart at: " + timeseries.getLocation());

		timeseries.setTitle("Mein Test Titel");
		timeseries.setHeight(768);
		timeseries.setWidth(1024);
		timeseries.setDataRefreshInterval(1000);
		//timeseries.setVisibleDatapointsLimit(10);

		ArrayList<TimeSeriesData> data = new ArrayList<>();
		
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			data.add(new TimeSeriesData(LocalDateTime.now().plusMinutes(2*i), (double) Math.abs(random.nextInt() % 100)));
		}
		
		timeseries.addData(data);
		
		VideoCreator vc = new VideoCreator(timeseries);
		
		vc.createVideo();
	}
}
