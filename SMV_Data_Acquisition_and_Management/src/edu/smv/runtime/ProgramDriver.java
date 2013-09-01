package edu.smv.runtime;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.smv.cape.Cape;
import edu.smv.data.DataNode;
import edu.smv.gui.MainFrame;
import edu.smv.networking.Server;

public class ProgramDriver {

	private static double REFRESH_RATE = .25 * 1000; // In secounds
	
	/**
	 * Program Driver
	 * @param args
	 */
	public static void main(String[] args) {
		List<DataNode> nodeList = Collections.synchronizedList(new LinkedList<DataNode>());
		
		Cape cape = new Cape();
		MainFrame gui = new MainFrame();
		Server server = new Server(nodeList);
		Runtime runtime = Runtime.getRuntime();
		
		// Start the server thread
		server.start();
		
		// Loop Variables
		DataNode currentNode = null;
		long lastTimeLong = Long.MIN_VALUE;
		
		// Run forever. The program will exit by an action listener in MainFrame.
		while(true){
			long currentTime = System.currentTimeMillis();
			
			if((currentTime - lastTimeLong) >= REFRESH_RATE || currentNode == null){
				lastTimeLong = currentTime;
				currentNode = cape.getDataNode();
				nodeList.add(currentNode);
			}
			
			gui.refresh(currentNode);
			
			// Don't let the heap explode!
			double maxHeapUsagePercent = 0.75;
			double usedMemory = runtime.totalMemory() - runtime.freeMemory();
			
			if(usedMemory / runtime.totalMemory() > maxHeapUsagePercent) {
				//TODO: Save list as a log file
				nodeList.clear();
			}
		}
	}
}
