package javad3.renderer.visuengine;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import javad3.d3objects.ClusteringVideo;
import javad3.d3objects.ClusteringVideo.ClusteringData;
import javad3.renderer.visuengine.D3ObjectImpl;

class ClusteringVideoImpl extends D3ObjectImpl implements ClusteringVideo {
		
	final static String TEMPLATE = "clusteringVideo";
	final static String UNIT = "d3js";
	
	private final static String OPTION_DATA_KEY = "data";
	private final static String OPTION_POINTS_KEY = "points";
	private final static String OPTION_CLUSTER_KEY = "cluster";
	private final static String OPTION_DATA_ENTRY_X = "x";
	private final static String OPTION_DATA_ENTRY_Y = "y";
	private final static String OPTION_DATA_ENTRY_RADIUS = "radius";
	private final static String OPTION_DATA_ENTRY_TIMESTAMP = "timeStamp";


	ClusteringVideoImpl(int id, VisuEngineRenderer visuEngineRenderer) {
		super(id, visuEngineRenderer);
	}
	
	@Override
	public void addData(List<ClusteringData> data, ArrayList<ArrayList<ClusteringData>> cluster) {
		JsonArrayBuilder jsonPointsArray = Json.createArrayBuilder();
		JsonArrayBuilder jsonOverallClusterArray = Json.createArrayBuilder();
		JsonArrayBuilder jsonClusterArray = Json.createArrayBuilder();

		for(ClusteringData entry : data) {
			jsonPointsArray = jsonPointsArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
		}
		
		for(ArrayList<ClusteringData> timeStamp : cluster) {
			for(ClusteringData entry : timeStamp) {
				jsonClusterArray = jsonClusterArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
			}
			jsonOverallClusterArray = jsonOverallClusterArray.add(jsonClusterArray);
			jsonClusterArray = Json.createArrayBuilder();
		}
		
		JsonObject concat_data = Json.createObjectBuilder().add(OPTION_POINTS_KEY, jsonPointsArray).add(OPTION_CLUSTER_KEY, jsonOverallClusterArray).build();
		
		String json = Json.createObjectBuilder().add(OPTION_DATA_KEY, concat_data).build().toString();
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
