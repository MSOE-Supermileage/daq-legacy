package edu.smv.dam.networking;

import java.net.*;
import java.util.List;
import java.io.*;

import edu.smv.common.data.DataNode;


/**
 * Server that will send out data node information over a socket.
 * @author Mark
 *
 */
public class Server extends Thread{
	private static int DEFUALT_PORT = 1234;
	
	private int port;
	private boolean runServer;
	private List<DataNode> nodeList;
	
	/**
	 * Constructor
	 * @param port
	 */
	public Server(List<DataNode> nodeList){
		this(nodeList, DEFUALT_PORT);
	}
	
	
	/**
	 * Constructor
	 * @param port
	 */
	public Server(List<DataNode> nodeList, int port){
		this.port = port;
		this.runServer = true;
		this.nodeList = nodeList;
	}


	/**
	 * Send a signal for the server to stop
	 * @return
	 */
	public void stopServer(){
		this.runServer = false;
	}
	
	
	/**
	 * Return the port the server is running on
	 * @return
	 */
	public synchronized int getPort(){
		return port;
	}
	
	
	/**
	 * @return the nodeList
	 */
	public synchronized List<DataNode> getNodeList() {
		return nodeList;
	}


	/**
	 * @param nodeList the nodeList to set
	 */
	public synchronized void setNodeList(List<DataNode> nodeList) {
		this.nodeList = nodeList;
	}


	/**
	 * Entry point for thread
	 */
	@Override
	public void run() {
		ServerSocket serverSocket;
		Socket clientSocket;
		
		try{
			// Start the server
			serverSocket = new ServerSocket(port);
			
			while(runServer){
				// Wait for a client to connect
				clientSocket = serverSocket.accept();
				
				// Kick off a new thread to handle the client
				(new ClientHandler(clientSocket)).start();
			}
	
		}catch(IOException e){
			e.printStackTrace();
		}
		
		runServer = false;
	}
	
	
	/**
	 * ClientHandler
	 * 
	 * Handle the client connected to the server.
	 * 
	 * Currently is just echoing text, but will be updated to
	 * send out serialized data nodes or a List of data nodes.
	 * @author Mark
	 *
	 */
	protected class ClientHandler extends Thread {
		private Socket client;
		
		/**
		 * Constructor
		 * @param client
		 */
		public ClientHandler(Socket client){
			this.client = client;
		}
		
		
		/**
		 * Entry point for thread
		 */
		@Override
		public void run() {
			try{
				// Get socket commication 
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
								
				while(runServer){
					//Echo
					try {
						Object objectReceived = in.readObject();
						
						// Output node
						boolean nodeSent = false;
						while(!nodeSent){
							try{
								DataNode outputNode = nodeList.get(nodeList.size()-1);
								out.writeObject(outputNode);
								nodeSent = true;
							} catch(IndexOutOfBoundsException e){
								/* Failed to get node. Loop again. */
							}
						}
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}catch (java.io.EOFException eof){
				/* Do Nothing */
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
