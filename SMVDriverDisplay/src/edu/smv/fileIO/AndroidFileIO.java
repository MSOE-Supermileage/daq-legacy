package edu.smv.fileIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
	
	
	/**
	 * readFile - Read a text file and return an array containing each line from the file in an index.
	 * @param file
	 * @return
	 */
	static public String[] readFile(File file){
		List<String> retVal = new LinkedList<String>();
		
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNext()){
				retVal.add(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return (String[]) retVal.toArray();
	}
	
	
	/**
	 * createDirectory(File dir) - create a new directory at the location specfied by the passed file.
	 * @param dir
	 * @return
	 */
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
	
	/**
	 * writeFile(File, String) - Write a new file.
	 * @param file
	 * @param data
	 * @return
	 */
	static public boolean writeFile(File file, String data){
		return write(file, data, false);
	}
	
	/**
	 * Append to a file or create the file if it doesn't exist.
	 * @param file
	 * @param data
	 * @return
	 */
	static public boolean appendFile(File file, String data){
		return write(file, data, true);
	}
	
	/**
	 * Private method that wrties to the storage device for both write and append file.
	 * @param file
	 * @param data
	 * @param appendFile
	 * @return
	 */
	static private boolean write(File file, String data, boolean appendFile){
		boolean retVal = false;
		
		String state = Environment.getExternalStorageState();
		boolean isExternalWrite = file.getPath().substring(0, getExternalStorageDirectory().getPath().length()).equalsIgnoreCase(
									getExternalStorageDirectory().getPath());
		
		if (isExternalWrite && Environment.MEDIA_MOUNTED.equals(state) || !isExternalWrite) {
		    // We can read and write the media
			
			BufferedWriter output = null;
			try{
				// The follow if block may not be needed, should be reviewed.
				if(!file.exists()){
					File parent = file.getParentFile();
					if(parent != null && !parent.exists()){
						createDirectory(parent);
					}
					file.createNewFile();
				}
				
				output = new BufferedWriter(new FileWriter(file, appendFile));
				output.write(data);
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
