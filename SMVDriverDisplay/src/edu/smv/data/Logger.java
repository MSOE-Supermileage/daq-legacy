package edu.smv.data;

import java.io.File;
import java.util.Calendar;

import android.content.Context;

import edu.smv.fileIO.AndroidFileIO;

public class Logger {
	private static String logCSVHeader = "Time,MPG,RPM,MPG";
	private static File logFileCSV;
	
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
	 * Log to a new file
	 */
	public static void createNewFileCSV(Context context){
		String time = "" + getLogTime();
		time = time.replaceAll(" ", "_");
		time = time.replaceAll(":", "-");
		String fileName = time + ".csv";
		File fileDirectory = Config.getLogDirectory(context);
		
		if(!fileDirectory.exists()){
			AndroidFileIO.createDirectory(fileDirectory);
		}
		
		logFileCSV = new File(fileDirectory, fileName);
		AndroidFileIO.writeFile(logFileCSV, Logger.logCSVHeader + "\n");
	}

	
	/**
	 * Get an isntance of the CSV Log File.
	 */
	public static File getCSVLogFile(){
		return Logger.logFileCSV;
	}
	
	
	public static String getCSVHeader(){
		return Logger.logCSVHeader;
	}
	
	
	public static void setCSVHeader(String header){
		Logger.logCSVHeader = header;
	}
	
	
	/**
	 * Log the current state of the database to the log file.
	 * @param dataBase
	 * @return boolean - Success of log
	 */
	public static boolean logToCSV(DataBase dataBase) {
		boolean retVal = true;
		
		for(DataNode node : dataBase.getDataNodes()){
			retVal = retVal && AndroidFileIO.appendFile(Logger.logFileCSV, Logger.getLogTime() + "," + node.getMpg()  + "," + node.getRpm()  + "," + node.getMpg() + "\n");
		}
		
		return retVal;
	}
	
}
