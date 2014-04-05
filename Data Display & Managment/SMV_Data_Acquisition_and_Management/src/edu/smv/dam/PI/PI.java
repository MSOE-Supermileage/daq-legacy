package edu.smv.dam.PI;

import jssc.SerialPortException;
import edu.smv.common.data.DataNode;


/**
 * API for getting I/O input from the raspberry PI.
 * 
 * @author Mark
 *
 */
public class PI {
	private int speed;
	private int rpm;
	private GPS gps;
	
	public PI()
	{
		this.speed = 0;
		this.rpm = 0;
		
		try {
			this.gps = new GPS("com4");
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
		double retVal = -1;
		
		try
		{
			retVal = this.gps.getLatitude();
		}
		catch(NullPointerException e){}
		
		return retVal;
	}

	
	/**
	 * @return the longitde in degrees.
	 */
	public double getLongitude() {
		double retVal = -1;
		
		try
		{
			retVal = this.gps.getLongitude();
		}
		catch(NullPointerException e){}
		
		return retVal;
	}

	
	/**
	 * @return the altitude in meters
	 */
	public double getAltitude() {
		double retVal = -1;
		
		try
		{
			retVal = this.gps.getAltitude();
		}
		catch(NullPointerException e){}
		
		return retVal;
	}
	
	
	/**
	 * @return A datanode populated with values from the cape.
	 */
	public DataNode getDataNode() {
		return new DataNode(getSpeed(), getRPM(), getPMSVoltage(), 
				getLatitude(), getLongitude(), getAltitude());
	}

}
