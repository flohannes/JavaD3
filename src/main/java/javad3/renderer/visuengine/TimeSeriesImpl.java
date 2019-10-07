package javad3.renderer.visuengine;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import javad3.d3objects.TimeSeries;
import javad3.renderer.visuengine.D3ObjectImpl;

class TimeSeriesImpl extends D3ObjectImpl implements TimeSeries {
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS");
	
	final static String TEMPLATE = "timeseries-fixed-data";
	final static String UNIT = "d3js";
	
	private final static String OPTION_DATA_KEY = "data";
	private final static String OPTION_DATA_ENTRY_KEY = "date";
	private final static String OPTION_DATA_ENTRY_VALUE = "value";

	TimeSeriesImpl(int id, VisuEngineRenderer visuEngineRenderer) {
		super(id, visuEngineRenderer);
	}
	
	@Override
	public void addData(List<TimeSeriesData> data) {
		JsonArrayBuilder jsonDataArray = Json.createArrayBuilder();
		
		for(TimeSeriesData entry : data) {
			jsonDataArray = jsonDataArray.add(Json.createObjectBuilder().add(OPTION_DATA_ENTRY_KEY, entry.getKey().format(dateTimeFormatter)).add(OPTION_DATA_ENTRY_VALUE, Double.toString(entry.getValue())));
		}
		
		String json = Json.createObjectBuilder().add(OPTION_DATA_KEY, jsonDataArray).build().toString();
		
		this.visuEngineRenderer.sendData(this.id, json);
	}

	@Override
	public String getLocation() {
		return this.visuEngineRenderer.getURLForChartIdUnitAndTemplate(this.id, UNIT, TEMPLATE);
	}
	
}
