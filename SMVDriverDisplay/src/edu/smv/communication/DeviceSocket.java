package edu.smv.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class DeviceSocket {
	private BluetoothSocket blueToothSocket;
	private BluetoothDevice device;
	private UUID deviceID;

	public DeviceSocket(String deviceAddress) throws IOException {
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		device = adapter.getRemoteDevice(deviceAddress);
		deviceID = new UUID(0,0);

		blueToothSocket = device.createRfcommSocketToServiceRecord(deviceID);
	}
	
	public BluetoothDevice getDevice(){
		return this.device;
	}
	
	public BluetoothSocket getBlueToothSocket(){
		return this.blueToothSocket;
	}
	
	public UUID getDeviceID(){
		return this.deviceID;
	}
	
	public byte[] loadValues(){
		OutputStream out = this.getOutputStream();
		InputStream in = this.getInputStream();
		byte[] dataBuffer = null;
		
		try {
			out.write(0);
			
			dataBuffer = new byte[4];
			in.read(dataBuffer, 0, dataBuffer.length);
			
			// Eat rest of bytes
			while(in.read() > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataBuffer;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////		Methods Wrapped from BloothSocket		//////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	public boolean connect(){
		boolean retVal = true;
		
		try {
			this.blueToothSocket.connect();
		} catch (IOException e) {
			retVal = false;
		}
		
		return retVal;
	}
	
	public boolean close(){
		boolean retVal = true;
		
		try {
			this.blueToothSocket.close();
		} catch (IOException e) {
			retVal = false;
		}
		
		return retVal;
	}
	
	public InputStream getInputStream(){
		InputStream retVal = null;
		
		try {
			retVal = this.blueToothSocket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	public OutputStream getOutputStream(){
		return getOutputStream();
	}
	
	public BluetoothDevice getRemoteDevice(){
		return getRemoteDevice();
	}
	
	public boolean isConnected(){
		return isConnected();
	}
}
