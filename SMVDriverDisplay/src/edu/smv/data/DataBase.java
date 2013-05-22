package edu.smv.data;

import edu.smv.communication.DeviceSocket;

public class DataBase {
	private final String arduinoAddress = "00:11:22:AA:BB:CC";
	private DeviceSocket arduino;
	
	private double rpm;
	private double mph;
	private double amph;
	private double mpg;
	private double batteryVoltage;
	private Logger logger;
	
	/**
	 * Default Constructor
	 */
	public DataBase(){
		this.rpm = 0;
		this.mpg = 0;
		this.mpg = 0;
		this.amph = 0;
		this.batteryVoltage = 0;
		this.logger = new Logger();
		
//		try {
//			this.arduino = new DeviceSocket(this.arduinoAddress);
//			this.arduino.connect();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	/**
	 * Load Data values from Arduino into database
	 * Not yet tested.
	 * This needs to be redesigned.
	 * @return
	 */
	public boolean loadFromArduino(){
		boolean retVal = false;		

//		byte[] data = this.arduino.loadValues();
//		if(data != null){
//			retVal = true;	
//			this.rpm = data[0];
//			this.mpg = data[1];
//			this.mpg = data[2];
//			this.batteryVoltage = data[3];	
//		} 
		
		// Temp Values
		this.mph++;
		this.rpm++;
		this.amph++;
		this.mpg++;
		
		return retVal;
	}

	
	/**
	 * Send data to the logger
	 * @return
	 */
	public boolean logData(){
		return logger.log(this);
	}
	
	
	//////////////////////// Getters and Setters /////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Override toString() method to format Data for logging.
	 */
	@Override
	public String toString(){
		return this.mph + "," + this.rpm + "," + this.mpg;
		
	}

	/**
	 * Getter for RPM
	 * @return
	 */
	public double getRpm() {
		return rpm;
	}

	/**
	 * Getter or MPH
	 * @return
	 */
	public double getMph() {
		return mph;
	}

	/**
	 * Getter for MPG
	 * @return
	 */
	public double getMpg() {
		return mpg;
	}

	/**
	 * Getter for battery voltage
	 * @return
	 */
	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	/**
	 * Getter for average mph
	 * @return
	 */
	public double getAmph() {
		return amph;
	}
	
	/**
	 * Getter for logger
	 * @return
	 */
	public Logger getLogger(){
		return this.logger;
	}

}
