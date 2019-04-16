package javad3;

public class Histogramm extends Chart {

	public Histogramm() {
		super("histogram.ejs");
	}
	
	public void addData(Double pData) {
		JavaD3.setOption(id, JavaD3.OPTION_DATA, String.format("%f", pData));
	}
	
}
