package edu.smv.runtime;

import java.util.LinkedList;
import java.util.List;

import edu.smv.data.DataNode;
import edu.smv.gui.MainFrame;

public class ProgramDriver {

	/**
	 * Program Driver
	 * @param args
	 */
	public static void main(String[] args) {
		List<DataNode> dataNodes = new LinkedList<DataNode>();
		DataNode currentNode = null;
		
		new MainFrame(dataNodes, currentNode);
	}

}
