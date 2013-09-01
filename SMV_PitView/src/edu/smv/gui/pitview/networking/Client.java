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
		input.close();
		output.close();
		socket.close();
	}
	
	
	public DataNode getDataNode() throws IOException{
		DataNode retVal = null;

    	try {
    		this.output.writeObject(new String("Any object will do."));
    		retVal = (DataNode) this.input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
}
