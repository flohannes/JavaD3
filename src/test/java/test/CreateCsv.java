package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateCsv {
	
	public static void main(String[] args) throws IOException {
		ArrayList<List<String>> rows = new ArrayList<List<String>>();
			
			rows.add(Arrays.asList("time", "radius", "x", "y"));
			FileWriter csvWriter = new FileWriter("testdata/noCluster.csv");

			for(int i = 0; i < 50; i++) {
				Random r = new Random();
				
				for(int j = 0; j < 0; j++) {
					
					rows.add( Arrays.asList(
							Integer.toString(i),
							"radius="+Float.toString(r.nextFloat()),
							Float.toString(r.nextFloat()),
							Float.toString(r.nextFloat())
						)
					);
				}
				
			}
			
			for (List<String> rowData : rows) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();
	}
}
