package javad3.renderer.node;

import javad3.d3objects.TimeSeries;

class TimeSeriesImpl extends D3ObjectImpl implements TimeSeries {
	
	final static String CHART_TYPE = "timeseries.ejs";
	
	private final static String OPTION_DATA_KEY = "date";
	private final static String OPTION_DATA_VALUE = "value";

	TimeSeriesImpl(int id, NodeRenderer nodeRenderer) {
		super(id, nodeRenderer);
	}
	
	@Override
	public void addData(String key, double value) {
		this.nodeRenderer.setOptions(this.id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {key, Double.toString(value).replace(",", ".")});
	}
}