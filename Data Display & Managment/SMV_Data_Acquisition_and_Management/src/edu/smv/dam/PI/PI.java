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
	private double wheelCircumference = 1;
	private double wheelShaftCircumference = 1;
	private double engineShaftCircumference = 1;
	
	private GPS gps;
	private Encoder wheelShaftEncoder;
	private Encoder engineShaftEncoder;
	
	public PI()
	{	
		this.engineShaftEncoder = new Encoder(engineShaftCircumference, 0);
		this.wheelShaftEncoder = new Encoder(wheelCircumference, 0);
		
		try {
			this.gps = new GPS("com5");
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
		double circumferenceRatio = wheelCircumference / wheelShaftCircumference;
		return circumferenceRatio * this.wheelShaftEncoder.getShaftSpeed();
	}
	
	
	/**
	 * @return the revolutions per minute.
	 */
	public double getRPM() 
	{
		return engineShaftEncoder.getShaftSpeed();
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
