# JavaD3
Making beautiful plots from Java using D3js.

## Prerequisites

1. After downloading / cloning the project run the following command in the server' s root directory to get all required dependencies:
<code>
npm install
</code>

## Usage

### Example Histogram Plot

Current Version:<br/>
<code>
Histogram c = new Histogram();
c.setTitle("My new Histogram");
c.addData(values);
c.getHtml()
</code>

Future Versions maybe:<br/>
<code>
c.savePng(outputPath+"/histogramPlot.png");
</code>


## How to add your own Diagram - Java Part

### Step 1
Use the ChartTemplate.java file as your start point

### Step 2
Change the chart type to whatever you want and keep it in mind for later e.g. scatterplot.ejs (the ".ejs" is very important)

### Step 3
Add some custom functionality. For inspirations have a look at the other diagrams.

### Step 4
You' re done ;)

## How to add your own Diagram - HTMl, CSS, JS Part

### Step 1
Create a file in the server' s ejs directory named like your chart type e.g. scatterplot.ejs

### Step 2
Add your html, css, js for your chart to this file.

### Step 3 (Optional)
If you want to use third party js or css libraries you can simply copy them into the according directory (css for obviously css or libs for javascript). To use these libraries in your ejs file just you can do it like that:

<code>
<script src="/libs/<your custom js library>.js"></script>

<link rel="stylesheet" type="text/css" href="/css/<your custom css file>.css" />
</code>

### Step 4
Add your custom chart class to the Charts.js file and don't forget to add it to the module.exports!

### Step 5
After adding all your code to the server you need to rebuild it. To do that you need node installed as well as pkg (https://www.npmjs.com/package/pkg) and run in the following command in the server's root directory:
<code>
pkg . --out-path ../client/javad3/bin
</code>

### Step 6
Now you should be able to use your diagram in in JavaD3 ;)

## Troubleshooting

### Error: eclipse selection does not contain a main type
Remove all unneeded sources from the build path and add the project's client folder as the only build path.