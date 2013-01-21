package edu.smv.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		
//		try {
//			this.arduino = new DeviceSocket(this.arduinoAddress);
//			this.arduino.connect();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		this.logger = new Logger(getHeader());
	}
	
	/**
	 * Load Data values from Arduino
	 * Not yet tested.
	 * This needs to be redesigned.
	 * @return
	 */
	public boolean loadFromArduino(){
		boolean retVal = false;		
//		OutputStream out = arduino.getOutputStream();
//		InputStream in = arduino.getInputStream();
//		
//		try {
//			out.write(0);
//			
//			byte[] dataBuffer = new byte[4];
//			in.read(dataBuffer, 0, dataBuffer.length);
//			
//			this.rpm = dataBuffer[0];
//			this.mpg = dataBuffer[1];
//			this.mpg = dataBuffer[2];
//			this.batteryVoltage = dataBuffer[3];
//			
//			// Eat rest of bytes
//			while(in.read() > 0);
//			
//			boolean retVal = true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
		boolean retVal = logger.addLine(this.toString());
		logger.flush();
		return retVal;
	}
	
	/**
	 * Returns the header string for what data is in the database.
	 * @return
	 */
	public String getHeader(){
		return "MPH,RPM,MPG";
	}
	
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

	public void createNewLog() {
		logger.createNewFile();
	}

}
