package edu.smv.dam.PI;

import jssc.*;

public class GPS {
	/**
	 * 
	 */
	private SerialPort serialPort;
	private String buffer = "";
	
	// GPS Data
	private double latitude;
	private double longitude;
	private double fixQuality;
	private double altitude;
	
	
	/**
	 * 
	 * @param args
	 */
	public void main(String args[])
	{
		
	}
	
	
	/**
	 * 
	 * @param port
	 * @throws SerialPortException
	 */
	public GPS(String port) throws SerialPortException
	{
		serialPort = new SerialPort(port);
		serialPort.openPort();
		serialPort.setParams(4800, 8, 1,0);
		
		serialPort.addEventListener(new SerialPortEventListener(){
			@Override
			public void serialEvent(SerialPortEvent e) {
				try {
					buffer += serialPort.readString();
				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(buffer.contains("\n"))	// New line received from GPS
				{
					buffer = buffer.replaceAll("\n", "");
					buffer = buffer.replaceAll("\r", "");
					String tokens[] = buffer.split(",");
					
					if(buffer.contains("$GPGGA"))
					{
						try { latitude 	= Double.parseDouble(tokens[2]);} catch (NumberFormatException ne) { latitude 	= -1; }
						try { longitude = Double.parseDouble(tokens[4]);} catch (NumberFormatException ne) { longitude = -1;  }
						try { fixQuality= Double.parseDouble(tokens[6]);} catch (NumberFormatException ne) { fixQuality= -1;  }
						try { altitude 	= Double.parseDouble(tokens[9]);} catch (NumberFormatException ne) { altitude	= -1; }
					} 
					
					buffer = "";
				}
			}
		});
	}

	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}


	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}


	/**
	 * @return the fixQuality
	 */
	public double getFixQuality() {
		return fixQuality;
	}
	

	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}
}
