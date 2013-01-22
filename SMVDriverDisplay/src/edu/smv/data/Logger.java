package edu.smv.data;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Logger {
	final String logFileHeader;
	
	private List<String> logBuffer;
	private boolean logInProcess;
	private boolean logBufferLocked;
	private File logFile;
	
	/**
	 * Get the current time from the logger.
	 * @return Time as a string.
	 */
	static private String getLogTime(){
		return "" + Calendar.getInstance().getTime();
	}
	
	/**
	 * Default constructor that makes the header line the date.
	 */
	public Logger(){
		this("" + getLogTime());
	}
	
	/**
	 * Constructor
	 */
	public Logger(String header){
		this.logBuffer = new LinkedList<String>();
		this.logBufferLocked = false;
		this.logFileHeader = "Time," + header;
	}
	
	
	/**
	 * Log to a new file
	 */
	public void createNewFile(){
		String time = "" + getLogTime();
		time = time.replaceAll(" ", "_");
		time = time.replaceAll(":", "-");
		String fileName = time + ".csv";
		File fileDirectory = new File(AndroidFileIO.getExternalStorageDirectory().getAbsoluteFile() + "/MSOE_SMV");
		
		if(!fileDirectory.exists()){
			AndroidFileIO.createDirectory(fileDirectory);
		}
		
		this.logFile = new File(fileDirectory, fileName);
		AndroidFileIO.writeFile(this.logFile, this.logFileHeader + "\n");
	}
	
	public boolean addLine(String line){
		boolean retVal = false;
		
		if(!logBufferLocked){
			retVal = logBuffer.add(line);
		}
		
		return retVal;
	}
	
	
	/**
	 * Append the data in log buffer to the log file
	 */
	public void flush(){
		while(!logBuffer.isEmpty()){
	    	AndroidFileIO.appendFile(logFile, getLogTime() + "," + logBuffer.remove(0) + "\n");
	    }
	}
	
	
	/**
	 * Flush the data using a seperate thread.
	 */
	public void flushThread(){
		Thread loggingThread = new Thread(new Runnable() {
			 public void run(){
				 //Wait until any previous threads are done accessing the log
				 while(logInProcess || !logFile.canWrite());
				 
				logBufferLocked = true;
				List<String> tempLog = new LinkedList<String>();
				tempLog.addAll(logBuffer);
				logBuffer.removeAll(tempLog);
				logBufferLocked = false;
				
			    logInProcess = true;
			    while(!tempLog.isEmpty()){
			    	AndroidFileIO.appendFile(logFile, getLogTime() + "," + tempLog.remove(0) + "\n");
			    }
			    logInProcess = false;
			 }
		});
		loggingThread.start();
	}
	
	/**
	 * Return if there's currently a thread logging information to the file
	 * @return
	 */
	 public boolean getLogInProcess(){
		 return this.logInProcess;
	 }
	 
	 /**
	  * Return the log file
	  * @return
	  */
	 public File getLogFile() {
			return this.logFile;
		}
	 
	/**
	 * Kill the logger thread.
	 */
	public void killLogger(){
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	
}
