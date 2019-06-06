package javad3.renderer.node;

import javad3.d3objects.D3Object;

abstract class D3ObjectImpl implements D3Object{
	
	private static final String OPTION_WIDTH = "width";
	private static final String OPTION_HEIGHT = "height";
	private static final String OPTION_TITLE = "title";
	private static final String OPTION_DATA_REFRESH_INTERVAL = "datarefreshinterval";
	private static final String OPTION_VISIBLE_DATAPOINTS_LIMIT = "visibledatapointslimit";
	
	int id;
	NodeRenderer nodeRenderer;
	
	D3ObjectImpl(int id, NodeRenderer nodeRenderer) {
		this.id = id;
		this.nodeRenderer = nodeRenderer;
	}
	
	@Override
	public String getHtml() {
		return this.nodeRenderer.getHtml(this.id);
	}
	
	@Override
	public void setWidth(int width) {
		this.nodeRenderer.setOption(this.id, OPTION_WIDTH, Integer.toString(width));
	}
	
	@Override
	public void setHeight(int height) {
		this.nodeRenderer.setOption(this.id, OPTION_HEIGHT, Integer.toString(height));
	}
	
	@Override
	public void setTitle(String title) {
		this.nodeRenderer.setOption(this.id, OPTION_TITLE, title);
	}
	
	@Override
	public String getLocation() {
		return this.nodeRenderer.getURLForChartId(this.id);
	}
	
	@Override
	public void setDataRefreshInterval(long millis) {
		this.nodeRenderer.setOption(this.id, OPTION_DATA_REFRESH_INTERVAL, Long.toString(millis));
	}
	
	@Override
	public void setVisibleDatapointsLimit(int limit) {
		this.nodeRenderer.setOption(this.id, OPTION_VISIBLE_DATAPOINTS_LIMIT, Integer.toString(limit));
	}
	
}