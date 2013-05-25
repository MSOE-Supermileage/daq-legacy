package edu.smv.data;

import java.io.File;
import java.util.Calendar;

import edu.smv.fileIO.AndroidFileIO;

public class Logger {
	final String logFileHeader;
	private File logFile;
	
	/**
	 * Returns the default locations for logs.
	 * @return
	 */
	static public File getDefualtLogDirectory(){
		return new File(AndroidFileIO.getExternalStorageDirectory().getAbsoluteFile() + "/MSOE_SMV");
	}
	
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
		this.logFileHeader = "Time,MPH,RPM,MPG"; 
	}
	
	
	 /**
	  * Return the log file
	  * @return
	  */
	 public File getLogFile() {
		return this.logFile;
	}
	
	
	/**
	 * Log to a new file
	 */
	public void createNewFile(){
		String time = "" + getLogTime();
		time = time.replaceAll(" ", "_");
		time = time.replaceAll(":", "-");
		String fileName = time + ".csv";
		File fileDirectory = Config.getLogDirectory();
		
		if(!fileDirectory.exists()){
			AndroidFileIO.createDirectory(fileDirectory);
		}
		
		this.logFile = new File(fileDirectory, fileName);
		AndroidFileIO.writeFile(this.logFile, this.logFileHeader + "\n");
	}

	
	/**
	 * Log the current state of the database to the log file.
	 * @param dataBase
	 * @return boolean - Success of log
	 */
	public boolean log(DataBase dataBase) {
			return AndroidFileIO.appendFile(this.logFile, Logger.getLogTime() + "," + dataBase.getMpg()  + "," + dataBase.getRpm()  + "," + dataBase.getMpg() + "\n");
	}
	
}
