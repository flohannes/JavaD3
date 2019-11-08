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

## How to use Clustering Templates

You can find 3 different interfaces for image generation, livestreaming and video generation for cluster data in the javad3/d3objects directory. You can use those or define your own ones. 

You can find 3 different ejs files that relate to clustering in the EJS Folder that must be addded to VisuEngine D3 Unit. Each of them can be used to visualize micro-cluster data in different ways.

### clusteringImage.ejs
Can be used to generate an image of cluster data. All data that is sent to the VisuEngine server will be visualized in one image of the chart.

### clusteringLiveStreaming.ejs
Can be used to generate a livestream of cluster data. The VisuEngine server will update the generated chart in a given interval "dataRefreshInterval" and it will take the latest sent datapoints and cluster for visualization. This means the data must be send to VisuEngine in separated packages by time.

### clusteringVideo.ejs
Can be used to generate a video of cluster data and is possible to use with the VideoGenerator. Important is, that as soon as the VisuEngine server will get a GET request for the video, no more incoming data will be updated and won't be shown in the chart. That means all data that has to be in the generated video, must be at VisuEngine before the rendering starts.

### What parameters to set for cluster visualization

|parameter 		|meaning   							|type 	|
|---			|---								|---	|
|title   		|sets a title for the chart					|string |
|height   		|defines the height in pixel of the chart			|number |
|width   		|defines the width in pixel of the chart			|number |
|maxX   		|defines the max number on the x axis				|number |
|minX   		|defines the min number on the x axis				|number |
|maxY   		|defines the max number on the y axis				|number |
|minY   		|defines the min number on the y axis				|number |
|pointColor   		|sets the datapoints to given color (valid css colors)		|string |
|clusterColor   	|sets the cluster to given color (valid css colors)		|string |
|yLabel   		|defines label for y axis					|string |    
|xLabel   		|defines label for x axis					|string |
|dataRefreshInterval   	|defines the interval in which the chart will be rerendered	|number |
|visibleDataPointsLimit |defines how many datapoints will be shown in the chart		|number |
|data   		|contains the data (datapoints and cluster)			|object | 

### Format of the data parameter

```
data: {
    points: [
       {
            x: 11.3,
            y: 23.2, 
            timeStamp: "t1 Zeitstempel fuer diesen Punkt"
        },
        {
            x: 3.3,
            y: 53.2, 
            timeStamp: "t2 Zeitstempel fuer diesen Punkt"
        }
    ],
     cluster: {
        t1: [
            {
                x: 15.3,
                y: 13.2, 
                radius: 5.0,
            },
            {
                x: 5.3,
                y: 43.2, 
                radius: 2.3,
            }
        ]
    }
}
```
