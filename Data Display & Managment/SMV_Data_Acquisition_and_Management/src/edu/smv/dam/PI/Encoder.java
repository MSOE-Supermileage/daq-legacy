package edu.smv.dam.PI;

import java.util.Date;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Encoder 
{
    private double shaftCircumference;
    private Date lastInterupt;
    private Date secoundToLastInterupt;
    private GpioPinDigitalInput hallEffectSensorPIN = null;
    
    
    /**
     * Constructor
     * @param shaftCircumference
     * @param gpioPIN
     */
    public Encoder(double shaftCircumference, int gpioPIN)
    {
    	// Set shaftCircumference
    	this.shaftCircumference = shaftCircumference;
    	
    	// Set the Encoder to a GPIO on the PI
    	provisionGPIO_PIN(getRaspPin(gpioPIN));
    	
    	// Initialize dates to the current time
    	this.lastInterupt = new Date();
    	this.secoundToLastInterupt = new Date();
    }
    

    /**
     * Get the time between rotations,
     * which is the time between pulses from the hall effect sensor
     * @return
     */
	private double getTimePerRotation()
    {
        return lastInterupt.getTime() - secoundToLastInterupt.getTime();
    }
    
    
	/**
	 * Get the speed of the shaft
	 * @return
	 */
    public double getShaftSpeed()
    {
    	double speed = 0;
    	
    	if(getTimePerRotation() != 0)
    	{
    		speed = shaftCircumference / getTimePerRotation();
    	}
    	
        return speed;
    }
    
    
    /**
     * Return the PIN instance for a pin number
     * @param gpioPIN
     * @return
     */
    private Pin getRaspPin(int gpioPIN) 
    {
    	Pin retVal;
    	
    	switch(gpioPIN)
    	{
    	case 1:
    		retVal = RaspiPin.GPIO_01;
    		break;
    	case 2:
    		retVal = RaspiPin.GPIO_02;
    		break;
    	case 3:
    		retVal = RaspiPin.GPIO_03;
    		break;
    	case 4:
    		retVal = RaspiPin.GPIO_04;
    		break;
    	case 5:
    		retVal = RaspiPin.GPIO_05;
    		break;
    	case 6:
    		retVal = RaspiPin.GPIO_06;
    		break;
    	case 7:
    		retVal = RaspiPin.GPIO_07;
    		break;
    	case 8:
    		retVal = RaspiPin.GPIO_08;
			break;
    	case 9:
    		retVal = RaspiPin.GPIO_09;
    		break;
    	case 10:
    		retVal = RaspiPin.GPIO_10;
    		break;
    	case 11:
    		retVal = RaspiPin.GPIO_11;
    		break;
    	case 12:
    		retVal = RaspiPin.GPIO_12;
    		break;
    	case 13:
    		retVal = RaspiPin.GPIO_13;
    		break;
    	case 14:
    		retVal = RaspiPin.GPIO_14;
    		break;
    	case 15:
    		retVal = RaspiPin.GPIO_15;
    		break;
    	case 16:
    		retVal = RaspiPin.GPIO_16;
    		break;
    	case 17:
    		retVal = RaspiPin.GPIO_17;
    		break;
    	case 18:
    		retVal = RaspiPin.GPIO_18;
    		break;
    	case 19:
    		retVal = RaspiPin.GPIO_19;
    		break;
    	case 20:
    		retVal = RaspiPin.GPIO_20;
    		break;
    	default:
    		retVal = null;
    		break;
    	}
    	
		return retVal;
	}
    
    
    /**
     * Provision a pin for the hall effect sensor
     * @param pin
     */
    private void provisionGPIO_PIN(Pin pin)
    {
    	// create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin as an input pin with its internal pull down resistor enabled
        hallEffectSensorPIN = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);

        // create and register gpio pin listener
        hallEffectSensorPIN.addListener(new GpioPinListenerDigital() 
        {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) 
            {
                // The shaft has made a revolution
            	if(event.getState().isHigh())
                {
                	secoundToLastInterupt = lastInterupt;
                	lastInterupt = new Date();
                }
            }
        });
    }
}
