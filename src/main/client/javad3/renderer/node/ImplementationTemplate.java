package javad3.renderer.node;

import javad3.d3objects.InterfaceTemplate;

class ImplementationTemplate extends D3ObjectImpl implements InterfaceTemplate {

	final static String CHART_TYPE = "charttemplate.ejs";
	
	private final static String CUSTOM_DATA_NAME = "data";
	private final static String CUSTOM_OPTION_NAME = "subtitle";
	
	ImplementationTemplate(int id, NodeRenderer nodeRenderer) {
		super(id, nodeRenderer);
	}

	@Override
	public void addData(/* data parameters */) {
		//this.nodeRenderer.setOption(this.id, CUSTOM_DATA_NAME, /* data values */ );
	}
	
	public void addCustomOption(String value) {
		nodeRenderer.setOption(id, CUSTOM_OPTION_NAME, value);
	}
}