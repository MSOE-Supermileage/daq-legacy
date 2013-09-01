package edu.smv.gui.networking;

import java.io.IOException;

import edu.smv.data.DataNode;
import edu.smv.gui.frames.*;

public class ThreadedClient extends Thread{
	private MainFrame main;
	private Client clientHandle;
	private boolean terminate = false;
	
	public ThreadedClient(String host, int port, MainFrame main) throws IOException{
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
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
