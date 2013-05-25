package edu.smv.data;

import java.io.File;

import edu.smv.android.R;
import edu.smv.fileIO.AndroidFileIO;

import android.content.Context;
import android.content.res.Resources;

public class Config {
	public final static String ARDUINO_ADDRESS_KEY = "Arduino_Address";
	public final static String REFRESH_RATE_KEY  = "Display_Refresh_Rate";
	public final static String LOG_RATE_KEY = "Log_Rate";
	public final static String LOG_DIRECTORY_KEY = "Log_Directory";
	public final static String  UUID_LOW_KEY = "Arduino_UUID_LOW";
	public final static String  UUID_HIGH_KEY = "Arduino_UUID_HIGH";
	
	private static String arudinoAddress;
	private static byte lowByteUUID;
	private static byte highByteUUID;
	private static double refreshRate;
	private static double logRate;
	private static File logDirectorty;
	
	
	/**
	 * Get the config file
	 * @return
	 */
	public static File getConfigFile(Context context){
		return new File(context.getFilesDir() + "/MSOE_SMV_CONFIG.ini");
	}
	
	
	/**
	 * Save the configurations to a config file
	 * @param context
	 */
	public static void saveConfigFile(Context context){
		String output = "";
		output += ARDUINO_ADDRESS_KEY + "=" + Config.getArdunioAddress() + "\n";
		output += REFRESH_RATE_KEY + "=" + Config.getRefreshRate() + "\n";
		output += LOG_RATE_KEY + "=" + Config.getLogRate() + "\n";
		output += LOG_DIRECTORY_KEY + "=" + Config.getLogDirectory() + "\n";
		output += UUID_HIGH_KEY + "=" + Config.getUUIDHigh() + "\n";
		output += UUID_LOW_KEY + "=" + Config.getUUIDLow() + "\n";
		
		File configDirectory = new File(Config.getConfigFile(context).getParent());
		if(!configDirectory.exists()){
			AndroidFileIO.createDirectory(configDirectory);
		}
		
		AndroidFileIO.writeFile(getConfigFile(context), output);
	}


	/**
	 * Load configurations from config file, or use defualt configurations
	 * @param context
	 */
	public static void loadCurrentConfig(Context context){
		if(Config.getConfigFile(context).exists()){
			Config.loadConfigurationFile(context);
		}else{
			Config.configLoadDefualts(context);
		}
	}
	
	
	/**
	 * Load configurations from context file
	 */
	public static void loadConfigurationFile(Context context){
		float refreshRate = -1;
		float logRate = -1;
		String logDirectory = null;
		String arduinoAddress = null;
		byte uuidHigh = 0;
		byte uuidLow = 0;
		
		for(String line : AndroidFileIO.readFile(getConfigFile(context))){
			String[] tokens = line.split("=");
			
			if(tokens[0].equalsIgnoreCase(REFRESH_RATE_KEY)){
				try{
					refreshRate = Float.parseFloat(tokens[1]);
				} catch(NumberFormatException e) {/* Do nothing. */}
			}else if (tokens[0].equalsIgnoreCase(LOG_RATE_KEY)){
				try{
					logRate = Float.parseFloat(tokens[1]);
				} catch(NumberFormatException e) {/* Do nothing. */}
			}else if (tokens[0].equalsIgnoreCase(LOG_DIRECTORY_KEY)){
				logDirectory = tokens[1];
			}else if (tokens[0].equalsIgnoreCase(ARDUINO_ADDRESS_KEY)){
				arduinoAddress = tokens[1];
			}else if (tokens[0].equalsIgnoreCase(UUID_LOW_KEY)){
				uuidLow = Byte.parseByte(tokens[1]);
			}else if (tokens[0].equalsIgnoreCase(UUID_HIGH_KEY)){
				uuidHigh = Byte.parseByte(tokens[1]);
			}
		}
		
		Config.setRefreshRate(refreshRate);
		Config.setLogRate(logRate);
		Config.setLogDirectory(new File(logDirectory));
		Config.setArdunioAddress(arduinoAddress);
		Config.setArdunioUUIDHigh(uuidHigh);
		Config.setArdunioUUIDLow(uuidLow);
	}
	
	
	/**
	 * Load the default configuration files from R.string.
	 * @param context
	 */
	public static void configLoadDefualts(Context context){
		// Since config file doesn't exist use default values in R.string.
		Resources r = context.getResources();
		double refreshRate = Double.parseDouble(r.getString(R.string.configRefreshRate));
		double logRate = Double.parseDouble(r.getString(R.string.configLogRate));
		File logDirectory = Logger.getDefualtLogDirectory();
		String arduinoAddress = r.getString(R.string.configArdunioAddress);
		byte uuidHigh = Byte.parseByte(r.getString(R.string.configUUIDHigh));
		byte uuidLow = Byte.parseByte(r.getString(R.string.configUUIDLow));
		
		Config.setRefreshRate(refreshRate);
		Config.setLogRate(logRate);
		Config.setLogDirectory(logDirectory);
		Config.setArdunioAddress(arduinoAddress);
		Config.setArdunioUUIDHigh(uuidHigh);
		Config.setArdunioUUIDLow(uuidLow);
	}
	
	
	/**
	 * Get the refresh rate from shared preferences
	 * @return
	 */
	public static double getRefreshRate(){
		return Config.refreshRate;
	}
	
	
	/**
	 * Set the refresh rate in shared preferences
	 * @param refreshRate
	 */
	public static void setRefreshRate(double refreshRate){
		Config.refreshRate = refreshRate;
	}
	
	
	/**
	 * Get the log rate from the shared preferences
	 * @return
	 */
	public static double getLogRate(){
		return Config.logRate;
	}
	
	
	/**
	 * Set the log rate in the shared preferences
	 * @param logRate
	 */
	public static void setLogRate(double logRate){
		Config.logRate = logRate;
	}
	
	
	/**
	 * Get the log directory from the shared preferences
	 * @return
	 */
	public static File getLogDirectory(){
		return Config.logDirectorty;
	}
	
	
	/**
	 * Set the log directory in the shared preferences
	 * @param logDir
	 */
	public static void setLogDirectory(File logDir){
		Config.logDirectorty = logDir;
	}
	
	
	/**
	 * Get the ardunio address from the shared preferences
	 * @return
	 */
	public static String getArdunioAddress(){
		return Config.arudinoAddress;
	}
	
	
	/**
	 * Set the ardunio address in the shared preferences
	 * @param address
	 */
	public static void setArdunioAddress(String address){
		Config.arudinoAddress = address;
	}

	/**
	 * Set the high byte for the UUID
	 * @param uuidHigh
	 */
	public static void setArdunioUUIDHigh(byte uuidHigh) {
		Config.highByteUUID = uuidHigh;
		
	}


	/**
	 * Set the high byte for the UUID
	 * @param uuidLow
	 */
	public static void setArdunioUUIDLow(byte uuidLow) {
		Config.lowByteUUID = uuidLow;
		
	}

	
	/**
	 * Get the high byte for the UUID
	 * @return
	 */
	public static byte getUUIDHigh() {
		return Config.highByteUUID;
	}
	
	
	/**
	 * Get the low byte for the UUID
	 * @return
	 */
	public static byte getUUIDLow() {
		return Config.lowByteUUID;
	}
}
