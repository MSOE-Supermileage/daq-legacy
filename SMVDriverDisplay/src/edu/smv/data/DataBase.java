package edu.smv.data;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

import edu.smv.android.AndroidUtil;
import edu.smv.communication.DeviceSocket;

public class DataBase {
	private DeviceSocket arduino;
	
	private Activity activity;
	private List<DataNode> dataNodes;
	
	
	/**
	 * Default Constructor
	 */
	public DataBase(Activity activity){
		this.activity = activity;
		this.dataNodes = new LinkedList<DataNode>();
		
//		try {
//			this.arduino = new DeviceSocket(activity);
//			this.arduino.connect();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	
	/**
	 * Reset all the values in the data base
	 */
	private void resetDataBase() {
		dataNodes.clear();
	}

	
	/**
	 * Load Data values from Arduino into database
	 * Not yet tested.
	 * This needs to be redesigned.
	 * @return
	 */
	public boolean loadFromArduino(){
		boolean retVal = false;		
//		double rpm, mpg, mph, bv;
//
//		byte[] data = this.arduino.loadValues();
//		if(data != null){
//			retVal = true;  
//			rpm = data[0];
//			mph = data[1];
//			mpg = data[2];
//			bv = data[3];
//		this.dataNodes.add(new DataNode(-1, mph, mpg, rpm, -1, bv));
//		} 
		
		this.dataNodes.add(new DataNode(-1, Math.random() * 100, Math.random() * 100, Math.random() * 100,
				Math.random() * 100, Math.random() * 100));
		return retVal;
	}

	
	/**
	 * Send data to the logger
	 * @return
	 */
	public boolean logData(boolean resetDataBase){
		boolean retVal = Logger.logToCSV(this);
		
		if(resetDataBase && retVal){
			this.resetDataBase();
		}else if(resetDataBase && !retVal){
			AndroidUtil.showMessage(this.activity, "Unable to log data, so database wasn't cleared.");
		}
		
		return retVal;
	}
	

	/**
	 * @return the dataNodes
	 */
	public List<DataNode> getDataNodes() {
		return dataNodes;
	}
	

	/**
	 * getLatestMPH() - Get the latest MPH
	 * @return
	 */
	public double getLatestMPH(){
		double retVal = -1;
		
		if(Config.getUseGPS(activity)){
			retVal = dataNodes.get(dataNodes.size()-1).getGpsMPH();
		}else{
			retVal = dataNodes.get(dataNodes.size()-1).getArduinoMPH();
		}
		
		return retVal;
	}

	
	public double getLatestRPM() {
		return dataNodes.get(dataNodes.size()-1).getRpm();
	}


	public double getLatestMPG() {
		return dataNodes.get(dataNodes.size()-1).getMpg();
	}


	public double getLatestAMPH() {
		return dataNodes.get(dataNodes.size()-1).getAmph();
	}


	public double getLatestBatteryVoltage() {
		return dataNodes.get(dataNodes.size()-1).getBatteryVoltage();
	}
	
}
