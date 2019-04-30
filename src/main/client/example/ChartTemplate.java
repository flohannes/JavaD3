package example;

import javad3.JavaD3;
import javad3.charts.Chart;

public class ChartTemplate extends Chart {

	private final static String CHART_TYPE = "charttemplate.ejs";
	private final static String CUSTOM_OPTION_NAME = "subtitle";

	
	
	public ChartTemplate(String pType) {
		super(CHART_TYPE);
	}

	
	public void addCustomOption(String value) {
		JavaD3.setOption(id, CUSTOM_OPTION_NAME, value);
	}
	
}
