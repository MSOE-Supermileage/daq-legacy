package edu.smv.dam.PI;


/*
 * FileIO I've already written. 
 * Try and use methods already defined here for writing to files.
 */
import edu.smv.common.fileIO.FileIO;


/**
 * General Purpose Input/Ouput for a Raspberry PI.
 * 
 * GPIO Explanation: http://en.wikipedia.org/wiki/General-purpose_input/output
 * Raspberry Pin Pinout: http://elinux.org/RPi_Low-level_peripherals
 * Raspberry PI GPIO: https://sites.google.com/site/semilleroadt/raspberry-pi-tutorials/gpio
 * 
 * @author tutkowskim
 * @author Hartline
 *
 */
public class GPIO {
	/**
	 * Set the direction of the pin to input or output.
	 * @param pinName
	 * @return
	 */
	public static boolean setDirection(String pinName){
		exportPin(pinName, true);
		
		// TODO: Set the pin's direction.
		
		exportPin(pinName, false);
		return false; //Return if the operation was successful
	}
	
	
	/**
	 * Set the pin's value.
	 * @param pinName
	 * @param value
	 * @return
	 */
	public static boolean setPin(String pinName, int value){
		exportPin(pinName, true);
		
		// TODO: Set the pin's value.
		
		exportPin(pinName, false);
		return false; //Return if the operation was successful
	}
	
	
	/**
	 * Read the pin's value.
	 * @param pinName
	 * @return
	 */
	public static int readPin(String pinName){
		exportPin(pinName, true);
		
		// TODO: Read the pin's value.
		
		exportPin(pinName, false);
		return -1;
	}
	
	
	/**
	 * Export or Unexport the pin to the file system.
	 * This is required to access the pin.
	 * @param pinName
	 * @param export
	 * @return
	 */
	private static boolean exportPin(String pinName, boolean export){
		// To unexport write the pin's number to /sys/class/gpio/unexport 
		// To export write the pin's number to /sys/class/gpio/export
		return false;
	}
}