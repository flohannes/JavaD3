package javad3.charts;

import javad3.JavaD3;

public class Clustering extends Chart {
	
	private final static String OPTION_DATA_KEY = "date";
	private final static String OPTION_DATA_VALUE = "value";

	public Clustering() {
		super("clustering.ejs");
	}
	
	public void addData(String pKey, Double pValue) {
		JavaD3.setOptions(id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {pKey, pValue.toString()});
	}
	
}
