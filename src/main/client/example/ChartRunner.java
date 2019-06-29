package example;

//import java.util.Random;

import javad3.*;
import javad3.charts.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChartRunner {
	
	public static void main(String[] args) {
		
		//server runs on port 8000 and debug messages are turned on
		JavaD3.initialize(8000, true);
		
		
		/*
		 * You can find your charts at:
		 * 
		 * http://localhost:8000/<chartID>
		 * 
		 * 
		 */
		
		
		//try different chart types
		//Histogram c = new Histogram();
		//BarChart c = new BarChart();
		//TimeSerie c = new TimeSerie();
		Clustering c = new Clustering();
		
		System.out.println("Chart ID: " + Integer.toString(c.getId()));
		
		//set chart parameters
		c.setTitle("Mein Test Titel");
		c.setHeight(500);
		c.setWidth(500);
		
//		//generate random data
//		Random r = new Random();
//		for (int i=0; i < 200; i++) {
//			
//			if (i % 20 == 0 && i != 0) {
//				c.addFrame();
//			}
//			//add data for histogram
//			//c.addData((double) r.nextInt() % 200);
//			
//			//add data for barchart or timeserie
//			c.addData(Integer.toString(i), (double) Math.abs(r.nextInt() % 100));
//		}

		
		String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\JavaD3\\src\\main\\client\\example\\Data\\pollutionData-0.csv";
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\JavaD3\\src\\main\\client\\example\\Data\\pollutionData-cabirch-result-0.csv";
        BufferedReader br_data = null;
        BufferedReader br_cluster = null;
        String data_line = "";
        String cluster_line = "";
        String csvSplitBy = ",";
        
        
        try {

            br_data = new BufferedReader(new FileReader(datapoints));
            br_cluster = new BufferedReader(new FileReader(cluster));
            int count = 0;
   
            ArrayList<String[]> current_data = new ArrayList<>();
            ArrayList<String[]> current_cluster = new ArrayList<>();
            
            
            // idee: map erstellen mit Datum als key 
            while ((data_line = br_data.readLine()) != null ) {
            	// && (cluster_line = br_cluster.readLine()) != null
            	if(count==200) break;
           
//            	String[] cluster_data = cluster_line.split(csvSplitBy);
                String[] data = data_line.split(csvSplitBy);
                
//                if(cluster_data[0].equals(data[0])) {
//            		br_data.mark(1000);
//            		current_cluster.add(cluster_data);
//                }else {
//                	br_data.reset();
                	if(current_data.size() >= 30) {
                    	current_data.remove(0);
                	}
                    current_data.add(data);
                    c.addFrame();
                    c.addData(current_data, current_cluster);
//                    c.addCluster(cluster_data[2], cluster_data[3], cluster_data[1]);

//                }
                count ++;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br_data != null) {
                try {
                    br_data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		//get html from chart
		//System.out.println(c.getHtml());
        WebDriverTools tools = new WebDriverTools();
		tools.captureGIF(c.getId(),500,500,200,500,true);
		
	}

}
