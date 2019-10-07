package javad3.d3objects;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSeries extends D3Object {
	
	public static class TimeSeriesData {
		LocalDateTime key;
		double value;
		
		public TimeSeriesData(LocalDateTime key, double value) {
			this.key = key;
			this.value = value;
		}
		
		public LocalDateTime getKey() {
			return this.key;
		}
		
		public double getValue() {
			return this.value;
		}
	}
	
	public void addData(List<TimeSeriesData> data);
	
	public void setDataRefreshInterval(long millis);
	
	public void setVisibleDatapointsLimit(int limit);
}