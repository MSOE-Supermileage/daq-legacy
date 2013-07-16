/**
 * 
 */
package edu.smv.data;

/**
 * @author Mark
 *
 */
public class DataNode {
	private double gpsMPH;
	private double arduinoMPH;
	private double mpg;
	private double rpm;
	private double amph;
	private double batteryVoltage;
	
	
	public DataNode( double gpsMPH, double arduinoMPH, double mpg, double rpm, double amph, double batteryVoltage) {
		this.gpsMPH = gpsMPH;
		this.arduinoMPH = arduinoMPH;
		this.mpg = mpg;
		this.rpm = rpm;
		this.amph = amph;
		this.batteryVoltage = batteryVoltage;
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
}
