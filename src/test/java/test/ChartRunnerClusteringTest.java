package test;

import javad3.d3objects.*;
import javad3.d3objects.ClusteringVideo.ClusteringData;
import javad3.renderer.visuengine.VisuEngineRenderer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChartRunnerClusteringTest {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		readPoints(args[0]);
		readCluster(args[1]);
	}
	
	public static void readData(String datapoints, String cluster) {        
        /*
         *  save all datapoints in a long list and save all cluster in a Map with date as key
         */
        readPoints(datapoints);
        readCluster(cluster);

	}
	
	@SuppressWarnings("finally")
	public static ArrayList<ClusteringData> readPoints(String datapoints) {
		ArrayList<ClusteringData> all_datapoints = new ArrayList<ClusteringData>();
		BufferedReader br_data = null;

		try {
	        br_data = new BufferedReader(new FileReader(datapoints));

			String data_line = "";
	        String csvSplitBy = ",";

	        /*
	         *  read the datapoints into a list
	         */
	        int count = 0;
	        while ((data_line = br_data.readLine()) != null) {        
	        	count++;
	        	if(count == 1) continue;

	        	String[] data = data_line.split(csvSplitBy);
	        	Float X = Float.parseFloat(data[2]);
	        	Float Y = Float.parseFloat(data[3]);

	            /*
	             * add the current datapoint to list
	             */
	            all_datapoints.add(new ClusteringData(X, Y, 0.0f, data[0]));
	        }
		}catch (FileNotFoundException e) {
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
            return all_datapoints;
        }   
	}
	
	@SuppressWarnings({ "finally", "resource" })
	public static HashMap<String, ArrayList<ClusteringData>> readCluster(String cluster){
		 BufferedReader br_cluster = null;
	     String cluster_line = "";
	     String csvSplitBy = ",";
	     HashMap<String, ArrayList<ClusteringData>> cluster_map = new HashMap<String, ArrayList<ClusteringData>>();

	     try {
	         br_cluster = new BufferedReader(new FileReader(cluster));

	         /*
	             *  read the cluster into a Map
	             */
	            int count = 0;
	            while((cluster_line = br_cluster.readLine()) != null ) {
	                count++;
	            	if(count == 1) continue;

	            	String[] cluster_data = cluster_line.split(csvSplitBy);
	                String cluster_date = cluster_data[0];
	            	ArrayList<ClusteringData> current_cl_points = new ArrayList<ClusteringData>();

	            	if(cluster_map.get(cluster_date) != null) {
	                	current_cl_points = cluster_map.get(cluster_date);
	                }
	            	Float X = Float.parseFloat(cluster_data[2]);
	            	Float Y = Float.parseFloat(cluster_data[3]);
	            	String[] radius = cluster_data[1].split("=");
	            	
	                current_cl_points.add(new ClusteringData(X, Y, Float.parseFloat(radius[1]), cluster_data[0]));
	                cluster_map.put(cluster_date, current_cl_points);

	            }
	    	 
	    	 return cluster_map;
	     }catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br_cluster != null) {
	                try {
	                    br_cluster.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            return cluster_map;
	        }   
	}
	
	public static void sendData(ArrayList<ClusteringData> all_datapoints, HashMap<String, ArrayList<ClusteringData>>cluster_map) {
		VisuEngineRenderer renderer = new VisuEngineRenderer("localhost", 8000);
		ClusteringVideo clusteringVideo = renderer.createClusteringVideo();
		clusteringVideo.addData(all_datapoints, cluster_map);

	}
}
