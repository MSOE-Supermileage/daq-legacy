package edu.smv.runtime;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.smv.cape.Cape;
import edu.smv.data.DataNode;
import edu.smv.gui.MainFrame;
import edu.smv.networking.Server;

public class ProgramDriver {

	/**
	 * Program Driver
	 * @param args
	 */
	public static void main(String[] args) {
		List<DataNode> nodeList = Collections.synchronizedList(new LinkedList<DataNode>());
		
		Cape cape = new Cape();
		MainFrame gui = new MainFrame();
		Server server = new Server(nodeList);
		
		// Start the server thread
		server.start();
		
		// Run forever. The program will exit by an action listener in MainFrame.
		while(true){
			DataNode currentNode = cape.getDataNode();
			gui.refresh(currentNode);
			nodeList.add(currentNode);
			
			// Refresh rate
			try {
				Thread.sleep((long) (.5 * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
