package javad3.video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javad3.d3objects.D3Object;

public class VideoCreator {
	
	private String videoBaseDirectory = "video";
	private String tempDirectory = "frames/chart";
	private int screenshotsPerInstance = 500;
	private int framerate = 25;
	private String outputFilePath = "output";
	
	private D3Object chart;
	
	public VideoCreator(D3Object chart) {
		this.chart = chart;
	}
	
	public void setScreenshotsPerInstance(int screenshotsPerInstance) {
		this.screenshotsPerInstance = screenshotsPerInstance;
	}
	
	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}
	
	public void setChart(D3Object chart) {
		this.chart = chart;
	}
	
	public void createVideo() {
		this.saveHTML();
		this.makeScreenshotsFromHTML();
		this.makeVideoFromScreenshots();
	}
	
	private boolean saveHTML() {
		ProcessBuilder processBuilder;
		Process process;
		try {
			processBuilder = new ProcessBuilder();
			processBuilder.directory(new File("./" + videoBaseDirectory));
			processBuilder.command("./" + videoBaseDirectory + "/phantomjs.exe", "saveHTML.js", this.chart.getLocation(), this.tempDirectory, Integer.toString(this.framerate));
			process = processBuilder.start();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			int test = process.waitFor();
			return test == 0;	
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean makeScreenshotsFromHTML() {
		int curr = 0;
		int start = 0;
		int max = this.screenshotsPerInstance - 1;
		boolean screenshotReturn;
		
		File file = Paths.get(videoBaseDirectory + "/" + this.tempDirectory + Integer.toString(curr) + ".html").toFile();
		
		while(file.exists()) {
			if (curr >= max) {
				screenshotReturn = this.makeScreenshots(start, curr);
				if (!screenshotReturn) {
					return false;
				}
				max = curr + this.screenshotsPerInstance;
				start = curr + 1;
			}
			
			curr++;
			file = Paths.get(videoBaseDirectory + "/" + this.tempDirectory + Integer.toString(curr) + ".html").toFile();
		}
		
		if(curr == start) {
			return true;
		}
		
		return this.makeScreenshots(start, curr-1);
	}
	
	private boolean makeScreenshots(int start, int end) {
		ProcessBuilder processBuilder;
		Process process;
		int processReturn;
		try {
			processBuilder = new ProcessBuilder();
			processBuilder.directory(new File("./" + videoBaseDirectory));
			processBuilder.command("./" + videoBaseDirectory + "/phantomjs.exe", "makeScreenshot.js", this.tempDirectory, this.tempDirectory, Integer.toString(start), Integer.toString(end));
			process = processBuilder.start();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			processReturn = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		if (processReturn != 0) {
			return false;
		}
		return true;
	}
	
	private boolean makeVideoFromScreenshots() {
		ProcessBuilder processBuilder;
		Process process;
		int processReturn;
		try {
			processBuilder = new ProcessBuilder();
			processBuilder.directory(new File("./" + videoBaseDirectory));
			processBuilder.command("cmd.exe", "/c", "ffmpeg.bat", this.tempDirectory, Integer.toString(this.framerate), this.outputFilePath);
			process = processBuilder.start();
			//process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "ffmpeg.bat", this.tempDirectory, Integer.toString(this.framerate), this.outputFilePath}, new String[]{}, new File("./video"));
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			processReturn = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		if (processReturn != 0) {
			return false;
		}
		return true;
	}
	
	
}