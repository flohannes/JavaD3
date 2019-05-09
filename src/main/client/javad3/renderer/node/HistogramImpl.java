package javad3.renderer.node;

import javad3.d3objects.Histogram;

class HistogramImpl extends D3ObjectImpl implements Histogram {

	final static String CHART_TYPE = "histogram.ejs";
	
	private static final String OPTION_DATA = "data";
	
	HistogramImpl(int id, NodeRenderer nodeRenderer) {
		super(id, nodeRenderer);
	}
	
	@Override
	public void addData(double data) {
		this.nodeRenderer.setOption(this.id, OPTION_DATA, Double.toString(data).replace(",", "."));
	}
}