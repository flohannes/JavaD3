package javad3.charts;

import javad3.JavaD3;
import javad3.charts.Chart;

public class Clustering extends Chart {

	private final static String CHART_TYPE = "clustering.ejs";
	private final static String CUSTOM_OPTION_NAME = "subtitle";
	private final static String OPTION_DATA_KEY = "date";
	private final static String OPTION_DATA_VALUE = "value";

	public Clustering() {
		super(CHART_TYPE);
	}

	
	
	public void addData(String pKey, Float pValue) {
		JavaD3.setOptions(id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {pKey, pValue.toString()});
	}
	
}
