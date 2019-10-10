package javad3.d3objects;

import java.util.ArrayList;
import java.util.List;

public interface ClusteringVideo extends D3Object {
	
	public static class ClusteringData {
		String timeStamp;
		float x;
		float y;
		float radius;
		
		public ClusteringData(float x, float y, float radius, String timeStamp) {
			this.x = x;
			this.y = y;
			this.radius = radius;
			this.timeStamp = timeStamp;
		}
		
		public float getX() {
			return this.x;
		}
		
		public float getY() {
			return this.y;
		}
		public float getRadius() {
			return this.radius;
		}
		public String getTimeStamp() {
			return this.timeStamp;
		}
	}
	
	public void addData(List<ClusteringData> data, ArrayList<ArrayList<ClusteringData>> cluster);
		
	public void setDataRefreshInterval(long millis);
	
	public void setMaxX(int maxX);
	
	public void setMaxY(int maxY);
	
	public void setMinX(int minX);
	
	public void setMinY(int minY);
	
	public void setVisibleDatapointsLimit(int limit);
}