package example;

import javad3.d3objects.*;
import javad3.d3objects.ClusteringVideo.ClusteringData;
import javad3.renderer.visuengine.VisuEngineRenderer;
import javad3.video.VideoCreator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class ChartRunnerClustering {

	public static void main(String[] args) throws InterruptedException {
		VisuEngineRenderer renderer = new VisuEngineRenderer("localhost", 8000);
		
		ClusteringVideo clusteringVideo = renderer.createClusteringVideo();
		
		System.out.println("You can find your chart at: " + clusteringVideo.getLocation());

		clusteringVideo.setTitle("Mein Test Titel");
		clusteringVideo.setHeight(768);
		clusteringVideo.setWidth(1024);
		clusteringVideo.setDataRefreshInterval(400);
		clusteringVideo.setVisibleDatapointsLimit(20);
		
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
        String location = "";
        /*
         *  save all datapoints in a long list and save all cluster in a Map with date as key
         */
        ArrayList<ClusteringData> all_datapoints = new ArrayList<ClusteringData>();
        HashMap<String, ArrayList<ClusteringData>> cluster_map = new HashMap<String, ArrayList<ClusteringData>>();
        
        try {

            br_data = new BufferedReader(new FileReader(datapoints));
            br_cluster = new BufferedReader(new FileReader(cluster));
            
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
            	 * search for the max width to set
            	 */
            	if(X>max_width) max_width = X;
            	if(Y>max_height) max_height = Y;
            	if(X<min_width) min_width = X;
            	if(Y<min_height) min_height = Y;
            	
                /*
                 * add the current datapoint to list
                 */
                all_datapoints.add(new ClusteringData(X, Y, 0.0f, data[0]));
            }
            
            /*
             *  read the cluster into a Map
             */
            count = 0;
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
            
    		clusteringVideo.setMaxX(Math.round(max_width)+10);
    		clusteringVideo.setMaxY(Math.round(max_height)+10);
    		clusteringVideo.setMinX(Math.round(min_width)-10);
    		clusteringVideo.setMinY(Math.round(min_height)-10);
            clusteringVideo.addData(all_datapoints, cluster_map);
            
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
//
//		VideoCreator vc = new VideoCreator(clusteringVideo);
//		
//		vc.createVideo();
	}
	
    
//    public static ArrayList<ArrayList<ClusteringData>> readMap(HashMap<String, ArrayList<ClusteringData>> mp) {
//    	ArrayList<ArrayList<ClusteringData>> all_cluster = new ArrayList<ArrayList<ClusteringData>>();
//        Iterator it = mp.entrySet().iterator();
//        while (it.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry)it.next();
//            all_cluster.add((ArrayList<ClusteringData>) pair.getValue());;
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//        return all_cluster;
//    }
}
