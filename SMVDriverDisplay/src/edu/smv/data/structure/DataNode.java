package edu.smv.data.structure;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mark
 *
 */
public class DataNode implements Serializable{
	private static final long serialVersionUID = -266995844146268302L;

	public static final String fileType = "5MVL0G";
	private String name;
	private Date date;
	private double gpsMPH;
	private double arduinoMPH;
	private double mpg;
	private double rpm;
	private double amph;
	private double batteryVoltage;
	
	public DataNode( double gpsMPH, double arduinoMPH, double mpg, double rpm, double amph, double batteryVoltage) {
		this.date = Calendar.getInstance().getTime();
		this.name = Calendar.getInstance().getTime().toString();
		this.gpsMPH = gpsMPH;
		this.arduinoMPH = arduinoMPH;
		this.mpg = mpg;
		this.rpm = rpm;
		this.amph = amph;
		this.batteryVoltage = batteryVoltage;
	}
	
	public String toString(){
		return "Name: " + this.name + "\n" + 
				"Date: " +  this.date + "\n" + 
				"GpsMPH: " + this.gpsMPH + "\n" + 
				"ArduinoMPH: " + this.arduinoMPH + "\n" + 
				"Average mph: " + this.amph + "\n" + 
				"Mpg: " + this.mpg + "\n" + 
				"Rpm: " + this.rpm +  "\n" + 
				"Battery Voltage" + this.batteryVoltage;
	}
	
	/**
	 * @return the gpsMPH
	 */
	public double getGpsMPH() {
		return gpsMPH;
	}
	/**
	 * @param gpsMPH the gpsMPH to set
	 */
	public void setGpsMPH(double gpsMPH) {
		this.gpsMPH = gpsMPH;
	}
	/**
	 * @return the arduinoMPH
	 */
	public double getArduinoMPH() {
		return arduinoMPH;
	}
	/**
	 * @param arduinoMPH the arduinoMPH to set
	 */
	public void setArduinoMPH(double arduinoMPH) {
		this.arduinoMPH = arduinoMPH;
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

	public String getName() {
		return this.name;
	}
}
