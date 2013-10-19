package edu.smv.gui.pitview.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.smv.common.data.DataNode;

public class Client {
	private String host;
	private int port;
	
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	
	public Client(String host, int port) throws IOException{
		this.host = host;
		this.port = port;
	}
	
	public void connect() throws IOException {
		socket = new Socket(this.host, this.port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	
	
	public void disconnect() throws IOException{
		if(input != null){
			input.close();
		}
		if(output != null){
			output.close();
		}
		if(socket != null){
			socket.close();
		}
	}
	
	
	public DataNode getDataNode() throws IOException{
		DataNode retVal = null;

    	try {
    		this.output.writeObject(new String("Any serialized object will do."));
    		
    		boolean objectReceived = false;
    		while(!objectReceived){
    			try {
    				retVal = (DataNode) this.input.readObject();
    				objectReceived = true;
    			} catch (java.io.EOFException e){
    				// The packet hasn't arrived yet
    			}
    		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
}
