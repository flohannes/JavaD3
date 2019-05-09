package javad3.renderer.node;

import javad3.d3objects.BarChart;

class BarChartImpl extends D3ObjectImpl implements BarChart{

	final static String CHART_TYPE = "barchart.ejs";
	
	private final static String OPTION_DATA_KEY = "key";
	private final static String OPTION_DATA_VALUE = "value";
	
	BarChartImpl(int id, NodeRenderer nodeRenderer) {
		super(id, nodeRenderer);
	}
	
	@Override
	public void addData(String key, double value) {
		this.nodeRenderer.setOptions(id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {key, Double.toString(value).replace(",", ".")});
	}
}