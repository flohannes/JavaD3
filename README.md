# JavaD3
Making beautiful plots from Java using D3js.

## Usage

### Histogram Plot
<code>
HistogramPlot plot = new HistogramPlot(x);

plot.savePng(outputPath+"/histogramPlot.png");
</code>
<br>
![alt text](https://github.com/flohannes/JavaD3/blob/master/src/main/java/fschmidt/javad3/example/plots/histogramPlot.png)

### Scatter Plot
<code>
ScatterPlot plot = new ScatterPlot(x, y);

plot.savePng(outputPath+"/scatterPlot.png");
</code>
<br>
![alt text](https://github.com/flohannes/JavaD3/blob/master/src/main/java/fschmidt/javad3/example/plots/scatterPlot.png)


### Time Series Plot
<code>
TimeseriesPlot plot = new TimeseriesPlot(y,x);

plot.savePng(outputPath+"/timeseriesPlot.png");
</code>
<br>
![alt text](https://github.com/flohannes/JavaD3/blob/master/src/main/java/fschmidt/javad3/example/plots/timeseriesPlot.png)

