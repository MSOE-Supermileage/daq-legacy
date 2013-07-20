package edu.smv.logview;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import edu.smv.data.structure.DataNode;

public class Main extends JFrame {
	private static final long serialVersionUID = -8572684314913790609L;
	private final String title = "DataView 5000";
	private List<DataNode> dataNodes;
	private DataNode currentNode;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	
	/**
	 * Constructor
	 */
	public Main(){
		this.setJFrameProperties();
		this.setJMenuBar(new MenuBar(this));
		//this.add(comp)
		this.pack();
	}

	private void setJFrameProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle(this.title);
		this.setVisible(true);
	}


	public List<DataNode> getDataNodes() {
		return dataNodes;
	}


	public void setDataNodes(List<DataNode> dataNodes) {
		this.dataNodes = dataNodes;
	}


	public DataNode getCurrentNode() {
		return currentNode;
	}


	public void setCurrentNode(DataNode currentNode) {
		this.currentNode = currentNode;
	}
}
