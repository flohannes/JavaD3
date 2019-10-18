package javad3.renderer;

import javad3.d3objects.TimeSeries;
import javad3.d3objects.ClusteringVideo;
import javad3.d3objects.ClusteringLiveStreaming;

public interface Renderer {

	public TimeSeries createTimeSeries();
	
	public ClusteringVideo createClusteringVideo();
	
	public ClusteringLiveStreaming createClusteringLiveStreaming();
}
