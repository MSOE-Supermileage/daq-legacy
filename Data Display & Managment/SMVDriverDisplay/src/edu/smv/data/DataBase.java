package edu.smv.data;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;


import edu.smv.android.*;
import edu.smv.communication.*;
import edu.smv.data.structure.DataNode;
import edu.smv.fileIO.FileIO;

public class DataBase {
	private DeviceSocket arduino;
	private LocationProvider locationProvider;
	
	private DataNode currentNode;
	private List<DataNode> dataNodes;
	
	private Activity activity;
	
	
	/**
	 * Default Constructor
	 */
	public DataBase(Activity activity){
		this.activity = activity;
		this.currentNode = null;
		this.resetDataBase();
		
		/*
		try {
			this.arduino = new DeviceSocket(this.activity);
			this.arduino.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		this.locationProvider = this.getLocationProvider();
	}
	
	
	/**
	 * Reset all the values in the data base
	 */
	public void resetDataBase() {
		this.dataNodes = new LinkedList<DataNode>();
	}

	
	/**
	 * Load Data values into database
	 */
	public void loadDataNode(){	
		double rpm, batteryVoltage;

		/*
		byte[] data = this.arduino.loadValues();
		if(data != null){
			rpm = data[0];
			batteryVoltage = data[1];
		} 
		*/
		
		// Fake Values for testing
		rpm = Math.round(Math.random() * 7000);
		batteryVoltage = Math.round(Math.random() * 12);
		
		Location location = new Location(this.locationProvider.getName());
		
		this.currentNode = new DataNode(location, rpm, batteryVoltage);
	}

	
	/**
	 * Send data to the logger
	 * @return
	 */
	public boolean logData(){	
		return this.dataNodes.add(this.currentNode);
	}
	

	/**
	 * @return the dataNodes
	 */
	public List<DataNode> getDataNodes() {
		return dataNodes;
	}


	/**
	 * @return the currentNode
	 */
	public DataNode getCurrentNode() {
		return currentNode;
	}


	/**
	 * Save the database
	 */
	public void saveNodes() {
		Runnable saveDataBase = new Runnable(){
			public void run() {
				String time = AndroidUtil.getTime();
				time = time.replaceAll(" ", "_");
				time = time.replaceAll(":", "-");
				final String fileName = time + "." + DataNode.FILE_TYPE;
				final File fileDirectory = Config.getLogDirectory(activity);
                
                if(!fileDirectory.exists()){
                	FileIO.createDirectory(fileDirectory);
                }
                
                final File file = new File(fileDirectory, fileName);
                FileIO.saveDataNodes(file, getDataNodes());
			}
		};
		
		Thread savingThread = new Thread(saveDataBase);
		savingThread.start();
	}
	
	
	/**
	 * Get a handle for the phones location provider
	 * 
	 * Gives a preference to providers that do not require cell or network service
	 * 
	 * @return locationProvider
	 */
	private LocationProvider getLocationProvider(){
		LocationManager manager =  (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
		LocationProvider locationProvider = null;
		

		List<String> providers = manager.getAllProviders();
		
		for(String provider: providers){
			System.err.println(provider);
			LocationProvider lp =  manager.getProvider(provider);
			
			if(locationProvider == null || (!lp.requiresCell() && !lp.requiresNetwork())){
				locationProvider = lp;
			}
		}
		
		return locationProvider;
	}
}
