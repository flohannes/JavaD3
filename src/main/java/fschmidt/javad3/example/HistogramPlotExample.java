package fschmidt.javad3.example;

import fschmidt.javad3.HistogramPlot;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;

/**
 *
 * @author fschmidt
 */
public class HistogramPlotExample {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String outputPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        int size = 50;
        double[] x = new double[size];
        for (int i = 0; i < size; i++) {
            x[i] = Math.random();
        }
        HistogramPlot plot = new HistogramPlot(x);
        plot.savePng(outputPath+"/histogramPlot.png");
    }
}
