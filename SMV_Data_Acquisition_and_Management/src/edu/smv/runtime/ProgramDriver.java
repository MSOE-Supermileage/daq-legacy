package edu.smv.runtime;

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
		Cape cape = new Cape();
		MainFrame gui = new MainFrame();
		Server server = new Server(1234);
		
		// Run forever. The program will exit by an action listener in MainFrame.
		while(true){
			DataNode node = cape.getDataNode();
			gui.refresh(node);
			
			
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
