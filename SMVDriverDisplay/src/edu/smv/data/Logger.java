package edu.smv.data;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import android.os.Environment;

public class Logger extends Thread {
	private Collection<String> logBuffer;
	private boolean logToSD;
	
	/**
	 * Default Constructor
	 */
	public Logger(){
		logBuffer = new LinkedList<String>();
	}
	
	/**
	 * Add the string to logBuffer for the thread to handle
	 * @param out
	 * @return
	 */
	public boolean logString(String out){
		// Will this execute if the thread is paused?
		boolean retVal = logBuffer.add(out);
		this.notify();
		return retVal;
	}
	
	/**
	 * Override the run() method from thread
	 */
	@Override
	public void run(){
//		while(true){
//			Calendar rightNow = Calendar.getInstance();
//			File file = new File("Change File Path.txt");
//			boolean appendFile = true;
//			
//    		while(appendFile){
//    			try {
//    				// Pause Runnable if there's nothing to log.
//    				if(logBuffer.isEmpty()){
//    					this.wait();
//    				}
//    				
//    				//Log Data append data to
//    			    
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//    			
//    		}
//    	}
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
	
}
