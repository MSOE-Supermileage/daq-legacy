package edu.smv.data;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Logger extends Thread {
	private List<String> logBuffer;
	private boolean logToSD;
	private boolean appendFile;
	private boolean running;
	
	/**
	 * Default Constructor
	 */
	public Logger(){
		this.logBuffer = new LinkedList<String>();
		this.running = true;
	}
	
	/**
	 * Add the string to logBuffer for the thread to handle
	 * @param out
	 * @return
	 */
	public boolean logString(String out){
		// Will this execute if the thread is paused?
		boolean retVal = logBuffer.add(out);
		//this.notify();
		return retVal;
	}
	
	/**
	 * Override the run() method from thread
	 */
	@Override
	public void run(){
		while(this.running){
			String fileName = "" + Calendar.getInstance() + ".txt";
			File fileDirectory = new File(AndroidFileIO.getExternalStorageDirectory().getAbsoluteFile() + "/MSOE_SMV");
			File logFile = new File(fileDirectory, fileName);
			AndroidFileIO.createDirectory(fileDirectory);
			
			appendFile = true;
			
    		while(this.running && this.appendFile){
    			try {
    				// Pause Runnable if there's nothing to log.
    				if(this.logBuffer.isEmpty()){
    					//this.wait();
    				}
    				
    				// Remove from buffer if a successful write occurs
    				if(AndroidFileIO.appendFile(logFile, this.logBuffer.get(0))){
    					this.logBuffer.remove(0);
    				}
				//} catch (InterruptedException e) {
				//	e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
    			
    		}
    	}
	}
	
	/**
	 * Getter for logToSD
	 * @return
	 */
	public boolean isLogToSD() {
		return logToSD;
	}

	/**
	 * Setter for logToSD
	 * @param logToSD
	 */
	public void setLogToSD(boolean logToSD) {
		this.logToSD = logToSD;
	}
	
	/**
	 * Log to a new file
	 */
	public void createNewFile(){
		this.appendFile = false;
	}
	
	/**
	 * Kill the logger thread.
	 */
	public void killLogger(){
		this.running = false;
		this.logString("Logger closed at " + System.currentTimeMillis() + ".");
	}
	
}
