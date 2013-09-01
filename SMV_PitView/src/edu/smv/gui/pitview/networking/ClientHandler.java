package edu.smv.gui.pitview.networking;

import java.io.IOException;

import edu.smv.common.data.DataNode;
import edu.smv.gui.pitview.frames.*;

public class ClientHandler extends Thread{
	private MainFrame main;
	private Client clientHandle;
	private boolean terminate = false;
	private long sleepTime;
	
	public ClientHandler(String host, int port, MainFrame main) throws IOException{
		this(host, port, 250, main);
	}
	
	public ClientHandler(String host, int port, long sleepTime, MainFrame main) throws IOException{
		this.main = main;
		clientHandle = new Client (host, port);
	}
	
	public void terminate(){
		this.terminate = true;
	}
	
	
	@Override
	public void run() {
		while(!this.terminate){
			
			try {
				this.clientHandle.connect();
				DataNode node = clientHandle.getDataNode();
				
				this.main.getDataNodes().add(node);
				this.main.setCurrentNode(node);
				
				this.main.refreshAll();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					this.clientHandle.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
