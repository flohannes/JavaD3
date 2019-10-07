package javad3.renderer.visuengine;

import javad3.d3objects.D3Object;

abstract class D3ObjectImpl implements D3Object {
	
	private static final String OPTION_WIDTH = "width";
	private static final String OPTION_HEIGHT = "height";
	private static final String OPTION_TITLE = "title";
	private static final String OPTION_DATA_REFRESH_INTERVAL = "dataRefreshInterval";
	private static final String OPTION_VISIBLE_DATAPOINTS_LIMIT = "visibleDatapointsLimit";
	
	int id;
	VisuEngineRenderer visuEngineRenderer;
	
	D3ObjectImpl(int id, VisuEngineRenderer visuEngineRenderer) {
		this.id = id;
		this.visuEngineRenderer = visuEngineRenderer;
	}
	
	@Override
	public String getHtml() {
		return this.visuEngineRenderer.getHtml(this.id);
	}
	
	@Override
	public void setWidth(int width) {
		this.visuEngineRenderer.setOption(this.id, OPTION_WIDTH, Integer.toString(width));
	}
	
	@Override
	public void setHeight(int height) {
		this.visuEngineRenderer.setOption(this.id, OPTION_HEIGHT, Integer.toString(height));
	}
	
	@Override
	public void setTitle(String title) {
		this.visuEngineRenderer.setOption(this.id, OPTION_TITLE, title);
	}
	
	@Override
	public void setDataRefreshInterval(long millis) {
		this.visuEngineRenderer.setOption(this.id, OPTION_DATA_REFRESH_INTERVAL, Long.toString(millis));
	}
	
	@Override
	public void setVisibleDatapointsLimit(int limit) {
		this.visuEngineRenderer.setOption(this.id, OPTION_VISIBLE_DATAPOINTS_LIMIT, Integer.toString(limit));
	}
	
}