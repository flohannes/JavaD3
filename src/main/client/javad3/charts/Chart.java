package javad3.charts;

import javad3.JavaD3;

public abstract class Chart {
	
	protected String type;
	protected int id;
	
	public Chart(String pType) {
		type = pType;
		id = JavaD3.createChart(type);
	}
	
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public String getHtml() {
		return JavaD3.getHtml(id);
	}
	
	public void setWidth(int pWidth) {
		JavaD3.setOption(id, JavaD3.OPTION_WIDTH, Integer.toString(pWidth));
	}
	
	public void setHeight(int pHeight) {
		JavaD3.setOption(id, JavaD3.OPTION_HEIGHT, Integer.toString(pHeight));
	}
	
	public void setTitle(String pTitle) {
		JavaD3.setOption(id, JavaD3.OPTION_TITLE, pTitle);
	}
	
	public void addFrame() {
		JavaD3.setOption(id, JavaD3.OPTION_ADD_FRAME, "1");
	}
	
}