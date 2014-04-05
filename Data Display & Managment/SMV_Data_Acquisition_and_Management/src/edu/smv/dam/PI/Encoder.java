package edu.smv.dam.PI;

import java.util.Date;
import com.pi4j.io.gpio.*;

public class Encoder 
{
    private double shaftCircumference;
    private Date lastInterupt;
    private Date secoundToLastInterupt;
    
    public Encoder(double shaftCircumference, int gpioPIN)
    {
    	// Set shaftCircumference
    	this.shaftCircumference = shaftCircumference;
    	
    	// Set the Encoder to a GPIO on the PI
    	//TODO: Set up the gpio port
    	
    	// Initialize dates to the current time
    	this.lastInterupt = new Date();
    	this.secoundToLastInterupt = new Date();
    }
    
    private double getTimePerRotation()
    {
        return lastInterupt.getTime() - secoundToLastInterupt.getTime();
    }
    
    
    public double getShaftSpeed()
    {
    	double speed = 0;
    	
    	if(getTimePerRotation() != 0)
    	{
    		speed = shaftCircumference / getTimePerRotation();
    	}
    	
        return speed;
    }
}
