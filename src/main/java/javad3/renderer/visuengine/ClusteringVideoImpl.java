package javad3.renderer.visuengine;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import javad3.d3objects.ClusteringVideo;
import javad3.d3objects.ClusteringVideo.ClusteringData;
import javad3.renderer.visuengine.D3ObjectImpl;

class ClusteringVideoImpl extends D3ObjectImpl implements ClusteringVideo {
		
	final static String TEMPLATE = "clusteringVideo";
	final static String UNIT = "d3js";
	
	private final static String OPTION_DATA_KEY = "data";
	private final static String OPTION_CLUSTER_KEY = "cluster";
	private final static String OPTION_DATA_ENTRY_X = "x";
	private final static String OPTION_DATA_ENTRY_Y = "y";
	private final static String OPTION_DATA_ENTRY_RADIUS = "radius";
	private final static String OPTION_DATA_ENTRY_TIMESTAMP = "timeStamp";


	ClusteringVideoImpl(int id, VisuEngineRenderer visuEngineRenderer) {
		super(id, visuEngineRenderer);
	}
	
	@Override
	public void addData(List<ClusteringData> data) {
		JsonArrayBuilder jsonDataArray = Json.createArrayBuilder();
		
		for(ClusteringData entry : data) {
			jsonDataArray = jsonDataArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
		}
		
		String json = Json.createObjectBuilder().add(OPTION_DATA_KEY, jsonDataArray).build().toString();
		
		this.visuEngineRenderer.sendData(this.id, json);
	}
	
	@Override
	public void addCluster(List<ClusteringData> cluster) {
		JsonArrayBuilder jsonDataArray = Json.createArrayBuilder();
		
		for(ClusteringData entry : cluster) {
			jsonDataArray = jsonDataArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_X, Float.toString(entry.getX())).add(OPTION_DATA_ENTRY_Y, Float.toString(entry.getY())).add(OPTION_DATA_ENTRY_RADIUS, Float.toString(entry.getRadius())).add(OPTION_DATA_ENTRY_TIMESTAMP, entry.getTimeStamp()));
		}
		
		String json = Json.createObjectBuilder().add(OPTION_CLUSTER_KEY, jsonDataArray).build().toString();
		
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
