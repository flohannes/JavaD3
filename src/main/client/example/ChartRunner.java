package example;

//import java.util.Random;

import javad3.*;
import javad3.charts.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

		
		String csvFile = "C:\\Users\\Laura\\Documents\\Uni\\JavaD3\\src\\main\\client\\example\\Data\\pollutionData-0.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {

            br = new BufferedReader(new FileReader(csvFile));
            int count = 0;
   
            while ((line = br.readLine()) != null) {
            	if(count==400) break;
            	if (count % 10 == 0 && count != 0) {
    				c.addFrame();
    			}
                String[] data = line.split(cvsSplitBy);
                double value = 0;
                try{
                    value = Double.valueOf(data[3]); 
                }catch(NumberFormatException ex){
                    System.err.print(data[3]);
                }  
                c.addData(data[2], value);
                count ++;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		//get html from chart
		//System.out.println(c.getHtml());
        WebDriverTools tools = new WebDriverTools();
		tools.captureGIF(c.getId(),500,500,40,250,true);
		
	}

}
