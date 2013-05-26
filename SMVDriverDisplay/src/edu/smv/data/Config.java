package edu.smv.data;

import java.io.File;

import edu.smv.android.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;

public class Config {
	public final static String ARDUINO_ADDRESS_KEY = "Arduino_Address";
	public final static String REFRESH_RATE_KEY  = "Display_Refresh_Rate";
	public final static String LOG_RATE_KEY = "Log_Rate";
	public final static String LOG_DIRECTORY_KEY = "Log_Directory";
	public final static String  UUID_KEY = "Arduino_UUID_STRING";
	
	public static final int MODE = Context.MODE_PRIVATE;
	public static final String PREF_NAME = "CONFIG";
	
	
	/**
	 * Get an instance of the shared preferences
	 * @param context
	 * @return
	 */
	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}
		
	
	/**
	 * Get an instance of the SharedPreference Editor
	 * @param context
	 * @return
	 */
	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}
	
	
	/**
	 * Get the refresh rate from shared preferences
	 * @return
	 */
	public static float getRefreshRate(Context context){
		return getPreferences(context).getFloat(Config.REFRESH_RATE_KEY, Config.getDefaultRefreshRate(context));
	}
	
	
	/**
	 * Set the refresh rate in shared preferences
	 * @param refreshRate
	 */
	public static void setRefreshRate(Context context, float refreshRate){
		getEditor(context).putFloat(Config.REFRESH_RATE_KEY, refreshRate).commit();
	}
	
	
	/**
	 * Get the default value for ArdunioAddress
	 */
	public static float getDefaultRefreshRate(Context context) {
		Resources r = context.getResources();
		return Float.parseFloat(r.getString(R.string.configRefreshRate));
	}
	
	
	/**
	 * Get the log rate from the shared preferences
	 * @return
	 */
	public static float getLogRate(Context context){
		return getPreferences(context).getFloat(Config.LOG_RATE_KEY, Config.getDefaultLogRate(context));
	}
	
	
	/**
	 * Set the log rate in the shared preferences
	 * @param logRate
	 */
	public static void setLogRate(Context context , float logRate){
		getEditor(context).putFloat(Config.LOG_RATE_KEY	, logRate).commit();
	}
	
	
	/**
	 * Get the default value for ArdunioAddress
	 */
	public static float getDefaultLogRate(Context context) {
		Resources r = context.getResources();
		return Float.parseFloat(r.getString(R.string.configLogRate));
	}
	
	
	/**
	 * Get the log directory from the shared preferences
	 * @return
	 */
	public static File getLogDirectory(Context context){
		return new File(getPreferences(context).getString(Config.LOG_DIRECTORY_KEY, Config.getDefualtLogDirectory(context).toString()));
	}
	
	
	/**
	 * Set the log directory in the shared preferences
	 * @param logDir
	 */
	public static void setLogDirectory(Context context, File logDir){
		getEditor(context).putString(Config.LOG_DIRECTORY_KEY, logDir.toString()).commit();
	}
	
	
	/**
	 * Get the default value for ArdunioAddress
	 */
	public static File getDefualtLogDirectory(Context context) {
		return Logger.getDefualtLogDirectory();
	}
	
	
	/**
	 * Get the ardunio address from the shared preferences
	 * @return
	 */
	public static String getArdunioAddress(Context context){
		return getPreferences(context).getString(Config.ARDUINO_ADDRESS_KEY, Config.getDefualtArdunioAddress(context));
	}
	
	
	/**
	 * Set the ardunio address in the shared preferences
	 * @param address
	 */
	public static void setArdunioAddress(Context context, String address){
		getEditor(context).putString(Config.ARDUINO_ADDRESS_KEY, address).commit();
	}
	
	
	/**
	 * Get the default value for ArdunioAddress
	 */
	public static String getDefualtArdunioAddress(Context context) {
		Resources r = context.getResources();
		return r.getString(R.string.configArdunioAddress);
	}


	/**
	 * Set the UUID
	 * @param uuidLow
	 */
	public static void setArdunioUUID(Context context, String uuid) {
		getEditor(context).putString(Config.UUID_KEY, uuid).commit();
	}

	
	/**
	 * Get the high byte for the UUID
	 * @return
	 */
	public static String getUUID_STRING(Context context) {
		return getPreferences(context).getString(Config.UUID_KEY, Config.getDefualtUUID_String(context));
	}
	
	/**
	 * Get the default value for UUID
	 */
	public static String getDefualtUUID_String(Context context) {
		Resources r = context.getResources();
		return r.getString(R.string.configArdunioUUID);
	}	
}
