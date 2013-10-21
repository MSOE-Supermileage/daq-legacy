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
	 * @param header
	 * @param pin
	 * @return
	 */
	public static boolean setDirection(int header, int pin){
		exportPin(header, pin, true);
		
		// TODO: Set the pin's direction.
		
		exportPin(header, pin, false);
		return false; //Return if the operation was successful
	}
	
	
	/**
	 * Set the pin's value.
	 * @param header
	 * @param pin
	 * @param value
	 * @return
	 */
	public static boolean setPin(int header, int pin, int value){
		exportPin(header, pin, true);
		
		// TODO: Set the pin's value.
		
		exportPin(header, pin, false);
		return false; //Return if the operation was successful
	}
	
	
	/**
	 * Read the pin's value.
	 * @param header
	 * @param pin
	 * @return
	 */
	public static int readPin(int header, int pin){
		exportPin(header, pin, true);
		
		// TODO: Read the pin's value.
		
		exportPin(header, pin, false);
		return -1;
	}
	
	
	/**
	 * Export or Unexport the pin to the file system.
	 * This is required to access the pin.
	 * @param pinName
	 * @param export
	 * @return
	 */
	private static boolean exportPin(int header, int pin, boolean export){
		// To unexport write the pin's number to /sys/class/gpio/unexport 
		// To export write the pin's number to /sys/class/gpio/export
		return false;
	}
}