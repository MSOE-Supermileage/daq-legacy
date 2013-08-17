package edu.smv.cape;

import edu.smv.data.DataNode;


/**
 * A cape is an expansion board for a beaglebone using it's IO connections.
 * This class is an api for a custom cape for our beaglebone black.
 * 
 * "Capes arn't just for super heros anymore!"
 * 
 * @author Mark
 *
 */
public class Cape {
	/**
	 * The speed in meters per hour.
	 * @return
	 */
	public double getSpeed(){
		return Math.random() * 190000;
	}
	
	
	/**
	 * @return the revolutions per minute.
	 */
	public double getRPM() {
		return Math.random() * 10000;
	}
	
	
	/**
	 * @return the batteryVoltage in volts.
	 */
	public double getPMSVoltage() {
		return -1;
	}

	
	/**
	 * @return the latitude in degrees.
	 */
	public double getLatitude() {
		return -1;
	}

	
	/**
	 * @return the longitde in degrees.
	 */
	public double getLongitude() {
		return -1;
	}

	
	/**
	 * @return the altitude in meters
	 */
	public double getAltitude() {
		return -1;
	}
	
	
	/**
	 * @return A datanode populated with values from the cape.
	 */
	public DataNode getDataNode() {
		return new DataNode(getSpeed(), getRPM(), getPMSVoltage(), 
				getLatitude(), getLongitude(), getAltitude());
	}

}
