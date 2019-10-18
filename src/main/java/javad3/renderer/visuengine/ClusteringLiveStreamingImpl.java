package javad3.renderer.visuengine;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import javad3.d3objects.ClusteringLiveStreaming;
import javad3.renderer.visuengine.D3ObjectImpl;

class ClusteringLiveStreamingImpl extends D3ObjectImpl implements ClusteringLiveStreaming {
		
	final static String TEMPLATE = "clusteringLiveStreaming";
	final static String UNIT = "d3js";
	
	private final static String OPTION_DATA_KEY = "data";
	private final static String OPTION_POINTS_KEY = "points";
	private final static String OPTION_CLUSTER_KEY = "cluster";
	private final static String OPTION_DATA_ENTRY_X = "x";
	private final static String OPTION_DATA_ENTRY_Y = "y";
	private final static String OPTION_DATA_ENTRY_RADIUS = "radius";
	private final static String OPTION_DATA_ENTRY_TIMESTAMP = "timeStamp";


	ClusteringLiveStreamingImpl(int id, VisuEngineRenderer visuEngineRenderer) {
		super(id, visuEngineRenderer);
	}
	
	@Override
	public void addData(ClusteringData data, ArrayList<ClusteringData> cluster, String timeStamp) {
		JsonArrayBuilder jsonPointsArray = Json.createArrayBuilder();
		JsonObjectBuilder jsonOverallCluster = Json.createObjectBuilder();
		JsonArrayBuilder jsonClusterArray = Json.createArrayBuilder();
			
		/*
		 * write the datapoints into a json object
		 */
		
		jsonPointsArray = jsonPointsArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(data.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(data.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(data.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, data.getTimeStamp()));
		
		/*
		 * write the cluster in a json object
		 */
	
		for(ClusteringData entry : cluster) {
			jsonClusterArray = jsonClusterArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
		}

		/*
		 * write the json array to the timestamp key into an json object
		 */
		jsonOverallCluster = jsonOverallCluster.add((String) timeStamp, jsonClusterArray);
		jsonClusterArray = Json.createArrayBuilder();
		
		/*
		 * concat datapoints and cluster into one json object
		 */
		JsonObject concat_data = Json.createObjectBuilder().add(OPTION_POINTS_KEY, jsonPointsArray).add(OPTION_CLUSTER_KEY, jsonOverallCluster).build();
		String json = Json.createObjectBuilder().add(OPTION_DATA_KEY, concat_data).build().toString();

		/*
		 * send data to visuengine
		 */
		this.visuEngineRenderer.sendData(this.id, json);
	}
	
	@Override
	public String getLocation() {
		return this.visuEngineRenderer.getURLForChartIdUnitAndTemplate(this.id, UNIT, TEMPLATE);
	}

	@Override
	public void setMaxX(int maxX) {
		String json = Json.createObjectBuilder().add("maxX", maxX).build().toString();

		this.visuEngineRenderer.sendData(this.id, json);		
	}

	@Override
	public void setMaxY(int maxY) {
		String json = Json.createObjectBuilder().add("maxY", maxY).build().toString();

		this.visuEngineRenderer.sendData(this.id, json);	
		
	}

	@Override
	public void setMinX(int minX) {
		String json = Json.createObjectBuilder().add("minX", minX).build().toString();

		this.visuEngineRenderer.sendData(this.id, json);	
		
	}

	@Override
	public void setMinY(int minY) {
		String json = Json.createObjectBuilder().add("minY", minY).build().toString();

		this.visuEngineRenderer.sendData(this.id, json);			
	}
	
}
