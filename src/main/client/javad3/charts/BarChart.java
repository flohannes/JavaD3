package javad3.charts;

import javad3.*;

public class BarChart extends Chart {
	
	private final static String OPTION_DATA_KEY = "key";
	private final static String OPTION_DATA_VALUE = "value";

	public BarChart() {
		super("barchart.ejs");
	}
	
	public void addData(String pKey, Double pValue) {
		JavaD3.setOptions(id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {pKey, pValue.toString()});
	}
	
	
	
}
