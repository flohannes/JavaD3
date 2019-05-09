package javad3.renderer.node;

import java.io.IOException;

public class LocalNodeServer {
	private static final String SERVER_BINARY_FOLDER = "./src/main/client/javad3/bin/";

	private static String host;
	private static int port;
	
	private static boolean debug = false;

	private static Process process;
	
	public static void setDebugMode(boolean debug) {
		LocalNodeServer.debug = debug;
	}

	public static boolean isDebugMode() {
		return debug;
	}

	public static void start(String host) {
		start(host, 8000);
	}

	public static void start(int port) {
		start("127.0.0.1", port);
	}

	public static void start(String host, int port) {
		LocalNodeServer.host = host;
		LocalNodeServer.port = port;

		String executable = getExecutableName();
		
		startServer(executable);
	}

	private static String getExecutableName() {
		String hostOS = System.getProperty("os.name").toLowerCase();
		
		return hostOS.contains("linux") ? "javad3-linux "
				: hostOS.contains("windows") ? "javad3-win.exe "
						: hostOS.contains("mac") ? "javad3-macos " : "OS nicht erkannt";
	}

	private static void startServer(String serverExecutable) {
		try {
			startServerProcess(serverExecutable);
		} catch (Exception e) {
			handleStartServerException(e, serverExecutable);
		}
	}

	private static void startServerProcess(String serverExecutable) throws IOException, InterruptedException {
		process = Runtime.getRuntime().exec(SERVER_BINARY_FOLDER + serverExecutable + String.valueOf(port));

		// TODO: Wait for server to start, instead of putting the thread to sleep
		Thread.sleep(2000);

		if (debug) {
			System.out.println(String.format("Chart server runs at %s:%d", host, port));
		}
	}

	private static void handleStartServerException(Exception e, String serverExecutable) {
		if (debug) {
			System.out.println("Chart server could not be started! Chosen Executable: " + serverExecutable);
		}
		
		e.printStackTrace();
	}

	public static void kill() {
		process.destroy();

		if (debug) {
			System.out.println("Chart server finished");
		}
	}
}