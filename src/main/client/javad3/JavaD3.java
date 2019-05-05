package javad3;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JavaD3 {
	
	public static final String SERVER_BINARY_FOLDER = "./src/main/client/javad3/bin/";
	
	public static final String OPTION_WIDTH = "width";
	public static final String OPTION_HEIGHT = "height";
	public static final String OPTION_TITLE = "title";
	public static final String OPTION_EJS_SCRIPT = "ejs";
	public static final String OPTION_DATA = "data";
	public static final String OPTION_ADD_FRAME = "addframe";

	private static String serverIp;
	private static Integer serverPort;
	private static Process serverProcess;
	private static Boolean debugMode;
	
	public static void initialize() {
		initialize(54321, false);
	}
	
	public static void initialize(Integer pLocalPort, Boolean pDebug) {
		
		serverIp = "127.0.0.1";
		serverPort = pLocalPort;
		debugMode = pDebug;
		
		String hostOS = System.getProperty("os.name").toLowerCase();
		String executable = hostOS.contains("linux") ? "javad3-linux " : 
			hostOS.contains("windows") ? "javad3-win.exe " :
				hostOS.contains("mac") ? "javad3-macos " : "OS nicht erkannt";
		
		try {
			serverProcess = Runtime.getRuntime().exec(SERVER_BINARY_FOLDER + executable + serverPort.toString());
			Thread.sleep(2000);
			if (debugMode) System.out.println(String.format("Chart server runs at %s:%d", serverIp, serverPort));
		} catch (Exception e) {
			if (debugMode) System.out.println("Chart server could not be started! Choosen Executable: " + executable);
			e.printStackTrace();
		}
		
	}
	
	public static void initialize(String pServerIp, Integer pServerPort, Boolean pDebug) {
		
		serverIp = pServerIp;
		serverPort = pServerPort;
		debugMode = pDebug;
		
	}
	
	public static void initialize(String pServerIp, Integer pServerPort) {
		initialize(pServerIp, pServerPort, false);
	}
	
	public static void deinitialize() {
		
		if (serverProcess != null) {
			serverProcess.destroy();
			if (debugMode) System.out.println("Chart server finished");
			
		}
		
	}

	
	/*
	 * Networking methods for client server communication.
	 */
	
	public static int createChart(String pType) {
		
		try {
		
		URL url = new URL(String.format("http://%s:%d/", serverIp, serverPort));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty(OPTION_EJS_SCRIPT, pType);
		
		if (conn.getResponseCode() == 201) {
			
			if (debugMode) System.out.println(String.format("Chart added of type %s", pType));
			
		} else {
			if (debugMode) System.out.println(String.format("Chart not added. Error code %d", conn.getResponseCode()));
			return -1;
		}
		
		return Integer.parseInt(conn.getHeaderField("Location"));
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static String getHtml(int pId) {
		
		try {
			
			URL url = new URL(String.format("http://%s:%d/%d", serverIp, serverPort, pId));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			if (conn.getResponseCode() != 200) return null;
			
			StringBuilder stringBuilder = new StringBuilder();
			InputStream is = conn.getInputStream();
			Scanner scanner = new Scanner(is);
			
			while (scanner.hasNext()) {
				stringBuilder.append(scanner.nextLine() + (scanner.hasNext() ? "\n" : ""));
			}
			
			scanner.close();
			
			return stringBuilder.toString();
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}

	public static Boolean setOptions(int pId, String[] pOptions, String[] pValues) {
		
		try {
			
			if (pOptions.length != pValues.length) return false;
			
			URL url = new URL(String.format("http://%s:%d/%d", serverIp, serverPort, pId));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			for (int i = 0; i < pOptions.length; i++) {
				conn.setRequestProperty(pOptions[i], pValues[i]);
			}
			
			return conn.getResponseCode() == 200;
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		
	}

	public static Boolean setOption(int pId, String pOption, String pValue) {
		return setOptions(pId, new String[] {pOption}, new String[] {pValue});
	}
}
