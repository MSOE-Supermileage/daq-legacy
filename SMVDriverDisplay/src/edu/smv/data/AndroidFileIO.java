package edu.smv.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.UnsupportedOperationException;

import android.os.Environment;

public class AndroidFileIO extends android.os.Environment {
	
	/* Static Methods inherited
	 *
	 * static File	 	getDataDirectory()			   	- Gets the Android data directory.
	 * static File	 	getDownloadCacheDirectory()   	- Gets the Android download/cache content directory.
	 * static File	 	getExternalStorageDirectory() 	- Gets the Android external storage directory.
	 * static File	 	getExternalStoragePublicDirectory(String type) - Get a top-level public external storage directory for placing files of a particular type.
	 * static String 	getExternalStorageState() 	   	- Gets the current state of the primary "external" storage device.
	 * static File	 	getRootDirectory() 		   		- Gets the Android root directory.
	 * static boolean 	isExternalStorageEmulated()		- Returns whether the device has an external storage device which is emulated.
	 * static boolean 	isExternalStorageRemovable()	- Returns whether the primary "external" storage device is removable.
	 */
	
	
	public AndroidFileIO(){
		super();
	}
	
	public String readFile(File file){
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
	
	static public boolean createDirectory(File dir){
		boolean retVal = false;
		try{
			if(!dir.exists())
				retVal = dir.mkdir();
		}catch(Exception e){
		  e.printStackTrace();
		}
		return retVal;
	}
	
	public boolean writeFile(File file, String data){
		return this.writer(file, data, false);
	}
	
	public boolean appendFile(File file, String data){
		return this.writer(file, data, true);
	}
	
	private boolean writer(File file, String data, boolean appendFile){
		boolean retVal = false;
		
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
			
			BufferedWriter output = null;
			try{
				if(!file.exists()){
					file.createNewFile();
				}
				
				output = new BufferedWriter(new FileWriter(file, appendFile));
				output.write(data);
				output.newLine();
				retVal = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return retVal;
	}

}
