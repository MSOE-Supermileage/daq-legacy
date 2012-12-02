package com.example.smvdriverdisplay;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import android.os.Environment;

public class DataBase {
	private double rpm;
	private double mph;
	private double mpg;
	private double batteryVoltage;
	
	private Logger logger;
	private Collection logBuffer;
	private boolean logToSD;
	
	public DataBase(){
		setLogToSD(true);
		logBuffer = new LinkedList();
		logger = new Logger();
		logger.run();
	}
	
	public boolean loadFromArduino(){
		//TODO: Get Real Values
		rpm = randomNumber(0, 999);
		mph = randomNumber(0, 999);
		mpg = randomNumber(0, 999);
		batteryVoltage = randomNumber(0, 12);
		return false;
	}
	
	private double randomNumber(double minNum, double maxNum){
		//TODO: REMOVE ME!
		return Math.round((maxNum - minNum) * Math.random() + minNum);
	}
	
	public boolean logData() {
		boolean retVal = logBuffer.add("DATAHERE");
		logger.notify();
		return retVal;
	}

	public double getRpm() {
		return rpm;
	}

	public double getMph() {
		return mph;
	}

	public double getMpg() {
		return mpg;
	}

	public double getBatteryVoltage() {
		return batteryVoltage;
	}
	
	public boolean isLogToSD() {
		return logToSD;
	}

	public void setLogToSD(boolean logToSD) {
		this.logToSD = logToSD;
	}

	private class Logger extends Thread{
		// Rewrite to follow the example @ http://www.java-samples.com/showtutorial.php?tutorialid=1523
    	public void run(){
    		while(true){
    			Calendar rightNow = Calendar.getInstance();
    			File file = new File(Environment.getExternalStoragePublicDirectory("MSOE SMV") + "//SMV " + rightNow.getTime() + ".txt");
    			boolean appendFile = true;
    			
	    		while(appendFile){
	    			try {
	    				// Pause Runnable if there's nothing to log.
	    				if(logBuffer.isEmpty()){
	    					this.wait();
	    				}
	    				
	    				//Log Data append data to
	    			    
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
	    			
	    		}
	    	}
		}
	 };

}
