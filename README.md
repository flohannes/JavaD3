# JavaD3
Making beautiful plots from Java using VisuEngine d3js.

## Prerequisites

1. Make sure you have an instance of VisuEngine (https://github.com/uniflo/VisuEngine) running and it has the d3js unit installed (https://github.com/uniflo/d3js).
2. Make sure the needed ejs templates are in the d3js unit. The ejs templates for this project are inside the ejs folder.

## Usage

### Example Timeseries Plot

```Java
VisuEngineRenderer renderer = new VisuEngineRenderer("localhost", 8000);
		
TimeSeries timeseries = renderer.createTimeSeries();

timeseries.setTitle("Title");
timeseries.setHeight(768);
timeseries.setWidth(1024);
timeseries.setDataRefreshInterval(1000);
timeseries.setVisibleDatapointsLimit(10);

timeseries.addData(data);
		
VideoCreator vc = new VideoCreator(timeseries);
		
vc.createVideo();
```

Depending on the length of the video, it can take a while until production is done.
You can find the video under "video/output.mp4".

## How to add your own Diagram - Template

1. Create an ejs template for the VisuEngine and make it available (See VisuEngine README).
2. In order to create videos the template needs to have a button with id "start" that starts the animation.
3. The visible area in the video is everything inside the element with id "chart", so make sure you assign the id to the SVG created by D3.

## How to add your own Diagram - Java

1. Create an interface for it (See javad3/d3objects/InterfaceTemplate.java or javad3/d3objects/TimeSeries.java)
2. Create an implementation (See javad3/renderer/visuengine/TimeSeriesImpl.java).
3. Implement the <code>addData</code> method in a way that it creates a JSON as your template expects.
4. Implement the <code>getLocation</code> method. Use the VisuEngineRenderer to retrieve the URL for your unit and template.
5. Create a method in the VisuEngineRenderer to create an instance of your new template. This is needed, as the VisuEngine assigns an id to a newly created chart that you need to store in your instance.