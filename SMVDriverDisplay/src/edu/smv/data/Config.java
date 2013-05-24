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
	
	/**
	 * Get the config file
	 * @return
	 */
	public static File getConfigFile(){
		return new File(Logger.getDefualtLogDirectory().getAbsolutePath() + "/MSOE_SMV_CONFIG.ini");
	}
	
	
	/**
	 * Save the configurations to a config file
	 * @param context
	 */
	public static void saveConfigFile(Context context){
		String output = "";
		output += ARDUINO_ADDRESS_KEY + "=" + Config.getArdunioAddress(context) + "\n";
		output += REFRESH_RATE_KEY + "=" + Config.getRefreshRate(context) + "\n";
		output += LOG_RATE_KEY + "=" + Config.getLogRate(context) + "\n";
		output += LOG_DIRECTORY_KEY + "=" + Config.getLogDirectory(context) + "\n";
		output += UUID_HIGH_KEY + "=" + Config.getUUIDHigh(context) + "\n";
		output += UUID_LOW_KEY + "=" + Config.getUUIDLow(context) + "\n";
		
		AndroidFileIO.writeFile(getConfigFile(), output);
	}


	/**
	 * Load configurations from config file, or use defualt configurations
	 * @param context
	 */
	public static void loadConfigFile(Context context){
		float refreshRate = -1;
		float logRate = -1;
		String logDirectory = null;
		String arduinoAddress = null;
		byte uuidHigh = 0;
		byte uuidLow = 0;
		
		if(Config.getConfigFile().exists()){
			
			for(String line : AndroidFileIO.readFile(getConfigFile())){
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
		}else{
			// Since config file doesn't exist use default values in R.string.
			Resources r = context.getResources();
			refreshRate = Float.parseFloat(r.getString(R.string.configRefreshRate));
			logRate = Float.parseFloat(r.getString(R.string.configLogRate));
			logDirectory = Logger.getDefualtLogDirectory().getAbsolutePath();
			arduinoAddress = r.getString(R.string.configArdunioAddress);
			uuidHigh = Byte.parseByte(r.getString(R.string.configUUIDHigh));
			uuidLow = Byte.parseByte(r.getString(R.string.configUUIDLow));
		}
		
		Config.setRefreshRate(context, refreshRate);
		Config.setLogRate(context, logRate);
		Config.setLogDirectory(context, logDirectory);
		Config.setArdunioAddress(context, arduinoAddress);
		Config.setArdunioUUIDHigh(context, uuidHigh);
		Config.setArdunioUUIDLow(context, uuidLow);
	}
	
	
	/**
	 * Get the refresh rate from shared preferences
	 * @param context
	 * @return
	 */
	public static float getRefreshRate(Context context){
		return SharedPreferances.readFloat(context, REFRESH_RATE_KEY, -1);
	}
	
	
	/**
	 * Set the refresh rate in shared preferences
	 * @param context
	 * @param refreshRate
	 */
	public static void setRefreshRate(Context context, float refreshRate){
		SharedPreferances.writeFloat(context, REFRESH_RATE_KEY, refreshRate);
	}
	
	
	/**
	 * Get the log rate from the shared preferences
	 * @param context
	 * @return
	 */
	public static float getLogRate(Context context){
		return SharedPreferances.readFloat(context, LOG_RATE_KEY, -1);
	}
	
	
	/**
	 * Set the log rate in the shared preferences
	 * @param context
	 * @param logRate
	 */
	public static void setLogRate(Context context, float logRate){
		SharedPreferances.writeFloat(context, LOG_RATE_KEY, logRate);
	}
	
	
	/**
	 * Get the log directory from the shared preferences
	 * @param context
	 * @return
	 */
	public static String getLogDirectory(Context context){
		return SharedPreferances.readString(context, LOG_DIRECTORY_KEY, null);
	}
	
	
	/**
	 * Set the log directory in the shared preferences
	 * @param context
	 * @param logDir
	 */
	public static void setLogDirectory(Context context, String logDir){
		SharedPreferances.writeString(context, LOG_DIRECTORY_KEY, logDir);
	}
	
	
	/**
	 * Get the ardunio address from the shared preferences
	 * @param context
	 * @return
	 */
	public static String getArdunioAddress(Context context){
		return SharedPreferances.readString(context, ARDUINO_ADDRESS_KEY, null);
	}
	
	
	/**
	 * Set the ardunio address in the shared preferences
	 * @param context
	 * @param address
	 */
	public static void setArdunioAddress(Context context, String address){
		SharedPreferances.writeString(context, ARDUINO_ADDRESS_KEY, address);
	}

	/**
	 * Set the high byte for the UUID
	 * @param context
	 * @param uuidHigh
	 */
	public static void setArdunioUUIDHigh(Context context, byte uuidHigh) {
		SharedPreferances.writeInteger(context, UUID_HIGH_KEY, uuidHigh);
		
	}


	/**
	 * Set the high byte for the UUID
	 * @param context
	 * @param uuidLow
	 */
	public static void setArdunioUUIDLow(Context context, byte uuidLow) {
		SharedPreferances.writeInteger(context, UUID_LOW_KEY, uuidLow);
		
	}

	
	/**
	 * Get the high byte for the UUID
	 * @param context
	 * @return
	 */
	public static byte getUUIDHigh(Context context) {
		return (byte) SharedPreferances.readInteger(context, UUID_HIGH_KEY, -1);
	}
	
	
	/**
	 * Get the low byte for the UUID
	 * @param context
	 * @return
	 */
	public static byte getUUIDLow(Context context) {
		return (byte) SharedPreferances.readInteger(context, UUID_LOW_KEY, -1);
	}
}
