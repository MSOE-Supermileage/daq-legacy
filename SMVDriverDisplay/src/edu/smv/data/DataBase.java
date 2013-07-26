package edu.smv.data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;


import edu.smv.android.*;
import edu.smv.communication.*;
import edu.smv.data.structure.*;
import edu.smv.fileIO.FileIO;

public class DataBase {
	private DeviceSocket arduino;
	
	private List<DataNode> dataNodes;
	private Activity activity;
	private DataNode currentNode;
	
	
	/**
	 * Default Constructor
	 */
	public DataBase(Activity activity){
		this.activity = activity;
		this.currentNode = null;
		this.resetDataBase();
		
		try {
			this.arduino = new DeviceSocket(this.activity);
			this.arduino.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Reset all the values in the data base
	 */
	public void resetDataBase() {
		this.dataNodes = new LinkedList<DataNode>();
	}

	
	/**
	 * Load Data values from Arduino into database
	 * Not yet tested.
	 * This needs to be redesigned.
	 * @return
	 */
	public boolean loadDataNode(){
		boolean retVal = false;		
		//double rpm, mpg, mph, bv;

		/*
		byte[] data = this.arduino.loadValues();
		if(data != null){
			retVal = true;  
			rpm = data[0];
			mph = data[1];
			mpg = data[2];
			bv = data[3];
		this.dataNodes.add(new DataNode(-1, mph, mpg, rpm, -1, bv));
		} 
		*/
		
		this.currentNode = new DataNode(-1, Math.random() * 100, Math.random() * 100, Math.random() * 100,
				Math.random() * 100, Math.random() * 100);
		return retVal;
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
				final String fileName = time + "." + DataNode.fileType;
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
	
}
