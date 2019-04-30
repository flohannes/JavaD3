package javad3.charts;

import javad3.JavaD3;

public class Histogram extends Chart {

	public Histogram() {
		super("histogram.ejs");
	}
	
	public void addData(Double pData) {
		JavaD3.setOption(id, JavaD3.OPTION_DATA, String.format("%f", pData));
	}
	
}
