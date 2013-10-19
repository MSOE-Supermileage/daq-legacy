package edu.smv.data.structure;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import android.location.Location;

/**
 * @author Mark
 *
 */
public class DataNode implements Serializable{
	private static final long serialVersionUID = -266995844146268302L;
	
	public static final double MILES_IN_A_METER = 0.000621371192;
	public static final String FILE_TYPE = "5MVL0G";
	
	private String name;
	private Date date;
	private double latitude;
	private double longitude;
	private double altitude;
	private double speed;
	private double mpg;
	private double rpm;
	private double amph;
	private double batteryVoltage;
	
	
	/**
	 * Constructor
	 * @param location
	 * @param mpg
	 * @param rpm
	 * @param amph
	 * @param batteryVoltage
	 */
	public DataNode(Location location, double rpm, double batteryVoltage) {
		this(	location.getSpeed(), rpm, batteryVoltage, 
				 location.getLatitude(), 
				 location.getLongitude(), 
				 location.getAltitude());
	}
	
	
	/**
	 * Constructor
	 * @param mph
	 * @param mpg
	 * @param rpm
	 * @param amph
	 * @param batteryVoltage
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 */
	public DataNode(double speed, double rpm, double batteryVoltage, double latitude, double longitude, double altitude){
		this.date = Calendar.getInstance().getTime();
		this.name = Calendar.getInstance().getTime().toString();
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		
		this.speed = speed;
		this.mpg = -1;	// Should be calculated
		this.amph = -1;	// Should be calculated
		this.rpm = rpm;
		this.batteryVoltage = batteryVoltage;
	}
	
	
	/**
	 * ToString method that displays all data in data node
	 */
	public String toString(){
		return "Name: " + this.getName() + "\n" +
				"Date: " +  this.getDate() + "\n" + 
				"Latitude: " +  this.getLatitude() + "\n" + 
				"Longitude: " +  this.getLongitude() + "\n" + 
				"Altitude: " +  this.getAltitude() + "\n" + 
				"Meters/Hour: " + this.getSpeed() + "\n" +
				"Miles/Hour: " + this.getMph() + "\n" + 
				"Average Miles/Hour: " + this.amph + "\n" + 
				"Meters/Gallon: " + this.mpg + "\n" + 
				"Rotations/Minute: " + this.rpm +  "\n" + 
				"Battery Voltage: " + this.batteryVoltage;
	}
	
	
	/**
	 * The speed in meters per hour
	 * @return
	 */
	public double getSpeed(){
		return this.speed;
	}
	
	
	/**
	 * @return The speed in miles per hour
	 */
	public double getMph() {
		return this.getSpeed() * DataNode.MILES_IN_A_METER;
	}

	
	/**
	 * @return What each node contains as a csv
	 */
	public static String getCSVheader() {
		return "Name,Data,mph,Average mph,Mpg,Rpm,Battery Voltage";
	}
	
	/**
	 * @return node as a csv
	 */
	public String getCSVline() {
		return this.name + "," + this.date + "," + this.getMpg() + "," + this.amph
				+ "," + this.mpg + "," + this.rpm + "," + this.batteryVoltage;
	}

	
	/**
	 * @return the mpg
	 */
	public double getMpg() {
		return mpg;
	}
	
	
	/**
	 * @param mpg the mpg to set
	 */
	public void setMpg(double mpg) {
		this.mpg = mpg;
	}
	
	
	/**
	 * @return the rpm
	 */
	public double getRpm() {
		return rpm;
	}
	
	
	/**
	 * @param rpm the rpm to set
	 */
	public void setRpm(double rpm) {
		this.rpm = rpm;
	}
	
	
	/**
	 * @return the amph
	 */
	public double getAmph() {
		return amph;
	}
	
	
	/**
	 * @param amph the amph to set
	 */
	public void setAmph(double amph) {
		this.amph = amph;
	}
	
	
	/**
	 * @return the batteryVoltage
	 */
	public double getBatteryVoltage() {
		return batteryVoltage;
	}
	
	
	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	
	/**
	 * @return the latitude in degrees
	 */
	public double getLatitude() {
		return this.latitude;
	}

	
	/**
	 * @return the longitde in degrees
	 */
	public double getLongitude() {
		return this.longitude;
	}

	
	/**
	 * @return the altitude in meters
	 */
	public double getAltitude() {
		return this.altitude;
	}
}
