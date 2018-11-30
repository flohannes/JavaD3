package fschmidt.javad3.example;

import fschmidt.javad3.TimeseriesPlot;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;

/**
 *
 * @author fschmidt
 */
public class TimeseriesPlotExample {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String outputPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        int size = 100;
        Date[] x = new Date[size];
        double[] y = new double[size];
        for (int i = 0; i < size; i++) {
            TimeUnit.SECONDS.sleep(1);
            x[i] = new Date();
            y[i] = Math.random();
        }
        TimeseriesPlot plot = new TimeseriesPlot(y,x);
        plot.savePng(outputPath+"/timeseriesPlot.png");
    }
}
