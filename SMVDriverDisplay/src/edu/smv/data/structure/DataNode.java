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

	public static final String fileType = "5MVL0G";
	private String name;
	private Date date;
	private Location location;
	private double mpg;
	private double rpm;
	private double amph;
	private double batteryVoltage;
	
	public DataNode(Location location, double mpg, double rpm, double amph, double batteryVoltage) {
		this.date = Calendar.getInstance().getTime();
		this.name = Calendar.getInstance().getTime().toString();
		this.location = location;
		this.mpg = mpg;
		this.rpm = rpm;
		this.amph = amph;
		this.batteryVoltage = batteryVoltage;
	}
	
	public String toString(){
		return "Name: " + this.getName() + "\n" +
				"Date: " +  this.getDate() + "\n" + 
				"Miles/Hour: " + this.getMph() + "\n" + 
				"Average Miles/Hour: " + this.amph + "\n" + 
				"Miles/Gallon: " + this.mpg + "\n" + 
				"Rotations/Minute: " + this.rpm +  "\n" + 
				"Battery Voltage: " + this.batteryVoltage;
	}
	
	
	/**
	 * @return The speed in miles per hour
	 */
	public double getMph() {
		final double MILES_IN_A_METER = 0.000621371192;
		return (this.location != null) ? (Math.round(this.location.getSpeed() * MILES_IN_A_METER)) : (-1.0) ;
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

	public double getLatitude() {
		return location.getLatitude();
	}

	public double getLongitude() {
		return location.getLongitude();
	}

	public double getAltitude() {
		return location.getAltitude();
	}
}
