package edu.smv.data;


public class DataBase {
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
	}
	
	/**
	 * Load Data values from audino
	 * Not yet implemented
	 * @return
	 */
	public boolean loadFromArduino(){
		//TODO: Get Real Values
		rpm = (rpm < 999) ? rpm + 1 : 0;
		mph = (mph < 999) ? mph + 1 : 0;
		mpg = (mpg < 999) ? mph + 1 : 0;
		amph = (amph < 999) ? mph + 1 : 0;
		batteryVoltage = (batteryVoltage < 12) ? mph + 1 : 0;
		return false;
	}
	
	/**
	 * Send data to the logger
	 * @return
	 */
	public boolean logData(){
		return logger.logString(this.toString());
	}
	
	/**
	 * Override toString() method to format Data for logging.
	 */
	@Override
	public String toString(){
		return this.mph + "/t"+ this.rpm + "/t" + this.mpg;
		
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

}
