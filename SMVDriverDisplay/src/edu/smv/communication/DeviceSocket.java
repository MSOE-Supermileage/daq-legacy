package edu.smv.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import edu.smv.data.Config;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

public class DeviceSocket {
        private BluetoothSocket blueToothSocket;
        private BluetoothDevice device;
        private UUID deviceID;

        /**
         * Constructor
         * @param context
         * @throws IOException
         */
        public DeviceSocket(Context context) throws IOException {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                device = adapter.getRemoteDevice(Config.getArdunioAddress(context));
                deviceID = UUID.fromString(Config.getUUID_STRING(context)); // A Serial over Bluetooth UUID
                blueToothSocket = device.createRfcommSocketToServiceRecord(deviceID);
        }
        
        /**
         * Get an Instance of the device
         * @return
         */
        public BluetoothDevice getDevice(){
                return this.device;
        }
        
        /**
         * Get an instance of the device socket
         * @return
         */
        public BluetoothSocket getBlueToothSocket(){
                return this.blueToothSocket;
        }
        
        /**
         * Get an instance of the UUID for this device
         * @return
         */
        public UUID getDeviceID(){
                return this.deviceID;
        }
        
        /**
         * Load values from the device
         * @return
         */
        public byte[] loadValues(){
                OutputStream out = this.getOutputStream();
                InputStream in = this.getInputStream();
                byte[] dataBuffer = null;
                
                try {
                        out.write(0);
                        
                        dataBuffer = new byte[4];
                        in.read(dataBuffer, 0, dataBuffer.length);
                        
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                return dataBuffer;
        }
        
        
        //////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////          Methods Wrapped from BloothSocket               //////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////
        
        
        /**
         * Connect to he bluetooth socket
         * @return
         */
        public boolean connect(){
                boolean retVal = true;
                
                try {
                        this.blueToothSocket.connect();
                } catch (IOException e) {
                        retVal = false;
                }
                
                return retVal;
        }
        
        
        /**
         * Close the bluetooth socket
         * @return
         */
        public boolean close(){
                boolean retVal = true;
                
                try {
                        this.blueToothSocket.close();
                } catch (IOException e) {
                        retVal = false;
                }
                
                return retVal;
        }
        
        
        /**
         * Get the input stream from the blueooth socket
         * @return
         */
        public InputStream getInputStream(){
                InputStream retVal = null;
                
                try {
                        retVal = this.blueToothSocket.getInputStream();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
                return retVal;
        }

        
        /**
         * Get the output stream from the bluetooth device
         * @return
         */
        public OutputStream getOutputStream(){
                try {
                        return blueToothSocket.getOutputStream();
                } catch (IOException e) {
                        for(int x = 0; x<25; x++)
                                System.err.println("Shouldn't happen but just in case... I'm in DeviceSocket.java");
                        e.printStackTrace();
                        return null;
                }
        }
        
        
        /**
         * Get the outputstream from the bluetooth device
         * @return
         */
        public BluetoothDevice getRemoteDevice(){
                return blueToothSocket.getRemoteDevice();
        }
        
        
        /**
         * Get if the bluetooth device connected
         * @return
         */
        public boolean isConnected(){
                return blueToothSocket.isConnected();
        }
}