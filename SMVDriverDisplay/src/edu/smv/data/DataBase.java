package edu.smv.data;


public class DataBase {
	private double rpm;
	private double mph;
	private double mpg;
	private double batteryVoltage;
	private Logger logger;
	
	/**
	 * Default Constructor
	 */
	public DataBase(){
		//logger.setLogToSD(true);
		//logger = new Logger();
		//logger.run();
	}
	
	/**
	 * Load Data values from audino
	 * Not yet implemented
	 * @return
	 */
	public boolean loadFromArduino(){
		//TODO: Get Real Values
		rpm = randomNumber(0, 999);
		mph = randomNumber(0, 999);
		mpg = randomNumber(0, 999);
		batteryVoltage = randomNumber(0, 12);
		return false;
	}
	
	/**
	 * Create a random number to mock data from arduino
	 * Remove this method once loadFromArduino() has been implemented
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	private double randomNumber(double minNum, double maxNum){
		//TODO: REMOVE ME!
		return Math.round((maxNum - minNum) * Math.random() + minNum);
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
		return null;
		
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

}
