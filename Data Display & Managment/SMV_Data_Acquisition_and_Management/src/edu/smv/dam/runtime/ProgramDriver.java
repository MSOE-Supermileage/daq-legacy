package edu.smv.dam.runtime;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.smv.common.data.DataNode;
import edu.smv.dam.cape.Cape;
import edu.smv.dam.gui.MainFrame;
import edu.smv.dam.networking.Server;

public class ProgramDriver {

	private static final double REFRESH_RATE = (1/4.0) * 1000; // In milliseconds
	private static final double MAX_HEAP_USAGE_PERCENT = 0.75;
	
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
			
			if(Math.abs(currentTime - lastTimeLong) >= REFRESH_RATE || currentNode == null){
				lastTimeLong = currentTime;
				currentNode = cape.getDataNode();
				nodeList.add(currentNode);
			}
			
			gui.refresh(currentNode);
			
			// Don't let the heap explode!
			double usedMemory = runtime.totalMemory() - runtime.freeMemory();
			
			if(usedMemory / runtime.totalMemory() > MAX_HEAP_USAGE_PERCENT) {
				//TODO: Save list as a log file
				nodeList.clear();
			}
		}
	}
}
