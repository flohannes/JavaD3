package fschmidt.javad3.example;

import fschmidt.javad3.MultiTimeseriesPlot;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;

/**
 *
 * @author fschmidt
 */
public class MultiTimeseriesPlotExample {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String outputPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        int size = 100;
        Date[] x = new Date[size];
        String[] dimNames = new String[]{"Series-A", "Series-B", "Series-C", "Series-D","Series-E"};
        double[] y1 = new double[size];
        double[] y2 = new double[size];
        double[] y3 = new double[size];
        double[] y4 = new double[size];
        double[] y5 = new double[size];
        for (int i = 0; i < size; i++) {
            TimeUnit.SECONDS.sleep(1);
            x[i] = new Date();
            y1[i] = Math.random() * 2;
            y2[i] = Math.random() * 10;
            y3[i] = Math.random() * 40;
            y5[i] = Math.random() * 100;
        }
        MultiTimeseriesPlot plot = new MultiTimeseriesPlot(x, dimNames, y1, y2, y3, y4,y5);
        plot.savePng(outputPath + "/multitimeseriesPlot.png");
    }
}
