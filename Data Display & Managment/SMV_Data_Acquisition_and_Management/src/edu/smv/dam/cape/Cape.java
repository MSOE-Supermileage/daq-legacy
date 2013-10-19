package edu.smv.dam.cape;

import edu.smv.common.data.DataNode;


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
	/*TEMP VALUES*/
	private static int speed = 0;
	private static int rpm = 0;
	
	/**
	 * The speed in meters per hour.
	 * @return
	 */
	public double getSpeed(){
		if(speed >= 120){
			speed = 0;
		}else{
			speed++;
		}
		return speed / DataNode.MILES_IN_A_METER;
	}
	
	
	/**
	 * @return the revolutions per minute.
	 */
	public double getRPM() {
		if(rpm >= 10000){
			rpm = 0;
		}else{
			rpm += 100;
		}
		return rpm;
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
