package fschmidt.javad3.example;

import fschmidt.javad3.BarPlot;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;

/**
 *
 * @author fschmidt
 */
public class BarPlotExample {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String outputPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        int size = 20;
        String[] x = new String[size];
        double[] y = new double[size];
        for (int i = 0; i < size; i++) {
            x[i] = ""+i;
            y[i] = Math.random()*100;
        }
        BarPlot plot = new BarPlot(x,y);
        plot.savePng(outputPath+"/barPlot.png");
    }
}
