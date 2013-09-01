package edu.smv.pitview.runtime;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.smv.common.data.DataNode;
import edu.smv.gui.pitview.frames.MainFrame;

public class ProgramDriver {

	/**
	 * Program Driver
	 * @param args
	 */
	public static void main(String[] args) {
		List<DataNode> dataNodes = Collections.synchronizedList(new LinkedList<DataNode>());
		DataNode currentNode = null;
		
		new MainFrame(dataNodes, currentNode);
	}

}
