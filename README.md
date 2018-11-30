# JavaD3
Making beautiful plots from Java using D3js.

## Usage

### Histogram Plot
<code>
HistogramPlot plot = new HistogramPlot(x);

plot.savePng(outputPath+"/histogramPlot.png");
</code>
<br>

### Scatter Plot
<code>
ScatterPlot plot = new ScatterPlot(x, y);

plot.savePng(outputPath+"/scatterPlot.png");
</code>
<br>

### Time Series Plot
<code>
TimeseriesPlot plot = new TimeseriesPlot(y,x);

plot.savePng(outputPath+"/timeseriesPlot.png");
</code>
