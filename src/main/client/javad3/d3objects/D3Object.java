package javad3.d3objects;

public interface D3Object {
	
	public void setHeight(int height);
	
	public void setWidth(int width);
	
	public void setTitle(String title);
	
	public String getHtml();
	
	public String getLocation();
	
	public void setDataRefreshInterval(long millis);
	
	public void setVisibleDatapointsLimit(int limit);
}