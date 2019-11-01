package example;

import javad3.d3objects.*;
import javad3.d3objects.ClusteringLiveStreaming.ClusteringData;
import javad3.renderer.visuengine.VisuEngineRenderer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChartRunnerClusteringLiveStreaming {

	public static void main(String[] args) throws InterruptedException {
		VisuEngineRenderer renderer = new VisuEngineRenderer("localhost", 8000);
		
		ClusteringLiveStreaming clusteringLiveStreaming = renderer.createClusteringLiveStreaming();
		
		System.out.println("You can find your chart at: " + clusteringLiveStreaming.getLocation());

		clusteringLiveStreaming.setTitle("Cluster Visualisierung");
		clusteringLiveStreaming.setHeight(528);
		clusteringLiveStreaming.setWidth(1024);
		clusteringLiveStreaming.setDataRefreshInterval(500);
		clusteringLiveStreaming.setVisibleDatapointsLimit(20);
		
		String datapoints = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\Data\\testData49.csv";
		String cluster = "C:\\Users\\Laura\\Documents\\Uni\\Bachelorarbeit\\Data\\testCluster49.csv";
        BufferedReader br_data = null;
        BufferedReader br_cluster = null;
        String data_line = "";
        String cluster_line = "";
        String csvSplitBy = ",";
        
        Float max_width = 0.00f;
      	Float max_height = 0.00f;
      	Float min_width = Float.POSITIVE_INFINITY;
      	Float min_height= Float.POSITIVE_INFINITY;
        /*
         *  save all datapoints in a long list and save all cluster in a Map with date as key
         */
        HashMap<String, ArrayList<ClusteringData>> cluster_map = new HashMap<String, ArrayList<ClusteringData>>();
        
        try {

            br_data = new BufferedReader(new FileReader(datapoints));
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
            	/*
            	 *  search for the max width to set
            	 */
            	if(X>max_width) max_width = X;
            	if(Y>max_height) max_height = Y;
            	if(X<min_width) min_width = X;
            	if(Y<min_height) min_height = Y;
            	
                current_cl_points.add(new ClusteringData(X, Y, Float.parseFloat(radius[1]), cluster_data[0]));
                cluster_map.put(cluster_date, current_cl_points);

            }
            
            /*
             *  read the datapoints and directly send them 
             *  and read the fitting cluster
             */
            count = 0;
            while ((data_line = br_data.readLine()) != null) {        
            	count++;
            	if(count == 1) continue;
            	String[] data = data_line.split(csvSplitBy);
            	Float X = Float.parseFloat(data[2]);
            	Float Y = Float.parseFloat(data[3]);
            	/* 
            	 * search for the max width to set
            	 */
            	if(X>max_width) max_width = X;
            	if(Y>max_height) max_height = Y;
            	if(X<min_width) min_width = X;
            	if(Y<min_height) min_height = Y;
            	
            	String timeStamp = data[0];
                /*
                 * add the current points and cluster to chart
                 */
            	
                ClusteringData currentCluster = new ClusteringData(X, Y, 0.0f, timeStamp);
                
        		clusteringLiveStreaming.setMaxX(Math.round(max_width)+10);
        		clusteringLiveStreaming.setMaxY(Math.round(max_height)+10);
        		clusteringLiveStreaming.setMinX(Math.round(min_width)-10);
        		clusteringLiveStreaming.setMinY(Math.round(min_height)-10);
                clusteringLiveStreaming.addData(currentCluster, cluster_map.get(timeStamp), timeStamp);
        		Thread.sleep(500);

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
	}
}
