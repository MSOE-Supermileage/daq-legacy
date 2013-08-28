package edu.smv.networking;

import java.net.*;
import java.io.*;


/**
 * Server that will send out data node information over a socket.
 * @author Mark
 *
 */
public class Server {
	private int port;
	private boolean runServer;
	private Thread serverThread;
	
	/**
	 * Constructor
	 * @param port
	 */
	public Server(int port){
		this.port = port;
		
		this.runServer = true;
		serverThread = new ServerThread();
		serverThread.start();
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
	public int getPort(){
		return port;
	}
	
	
	/**
	 * ServerThread
	 * 
	 * A thread will wait for a client to connect, 
	 * and kick of a client handler for each client that connects.
	 * @author Mark
	 *
	 */
	protected class ServerThread extends Thread {
		
		/**
		 * Entry point for thread
		 */
		@Override
		public void run() {
			ServerSocket serverSocket;
			Socket clientSocket;
			
			try{
				//Start the server
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
					PrintWriter out = new PrintWriter(client.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String inputLine;
					
					while(runServer){
						//Echo
						inputLine = in.readLine();
					    out.println(inputLine);
					}
				} catch(IOException e){
					e.printStackTrace();
				}
			}
			
		}
	}
}
