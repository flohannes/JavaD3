package javad3.renderer;

import javad3.d3objects.TimeSeries;
import javad3.d3objects.ClusteringVideo;

public interface Renderer {

	public TimeSeries createTimeSeries();
	
	public ClusteringVideo createClusteringVideo();
}
