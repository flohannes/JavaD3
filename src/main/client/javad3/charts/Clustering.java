package javad3.charts;

import java.util.ArrayList;

import javad3.JavaD3;

public class Clustering extends Chart {
	
	private final static String OPTION_DATA_KEY = "date";
	private final static String OPTION_DATA_VALUE = "value";

	public Clustering() {
		super("clustering.ejs");
	}
	
	public void addData(ArrayList<String[]> data, ArrayList<String[]> cluster) {
		data.forEach((n) -> JavaD3.setOptions(id, new String[] {OPTION_DATA_KEY, OPTION_DATA_VALUE}, new String[] {n[2], n[3]}));
		
	}
	
	public void addCluster(String pValue1, String pValue2, String pRadius) {
	}
	
}
