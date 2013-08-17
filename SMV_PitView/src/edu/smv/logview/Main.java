package edu.smv.logview;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ToolTipManager;

import edu.smv.data.*;
import edu.smv.gui.DriverDisplay;

public class Main extends JFrame {
	private static final long serialVersionUID = -8572684314913790609L;
	private final String title = "DataView 5000";
	private MapPanel mapPanel;
	private ListPanel listPanel;
	private DataPanel dataPanel;
	private GraphPanel graphPanel;
	private DriverDisplay driverDisplay;
	
	private List<DataNode> dataNodes;
	private DataNode currentNode;

	/**
	 * Program Driver
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	
	/**
	 * Constructor
	 */
	public Main(){
		// Prevent tooltips and menus from being light weight so they get drawn over the Worldwind canvas
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		
		this.dataNodes = new LinkedList<DataNode>();
		
		this.mapPanel = new MapPanel(this);
		this.listPanel = new ListPanel(this);
		this.dataPanel = new DataPanel(this);
		this.graphPanel = new GraphPanel();
		this.driverDisplay = new DriverDisplay();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Map", this.mapPanel);
		tabbedPane.addTab("DriverDisplay", this.driverDisplay);
		tabbedPane.addTab("Graph", this.graphPanel);
		
		JSplitPane northSouthsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, this.dataPanel);
		JSplitPane westEastsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, northSouthsplitPane, this.listPanel);
	
		this.setJFrameProperties();
		this.setJMenuBar(new MenuBar(this));
		this.add(westEastsplitPane);
		
		this.pack();
	}

	
	/**
	 * Set the properties for this JFrame
	 */
	private void setJFrameProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.title);
		this.setVisible(true);
	}


	/**
	 * Getter for the dataNodes
	 * @return
	 */
	public List<DataNode> getDataNodes() {
		return dataNodes;
	}


	/**
	 * Setter for the dataNodes
	 * @param dataNodes
	 */
	public void setDataNodes(List<DataNode> dataNodes) {
		this.dataNodes = dataNodes;
	}


	/**
	 * Getter for the current node
	 * @return
	 */
	public DataNode getCurrentNode() {
		return currentNode;
	}


	/**
	 * Setter for the current node
	 * @param currentNode
	 */
	public void setCurrentNode(DataNode currentNode) {
		this.currentNode = currentNode;
		this.driverDisplay.refresh(getCurrentNode());
		this.dataPanel.refreshData();
	}


	/**
	 * Refresh everything.
	 */
	public void refreshAll() {
		this.listPanel.refreshList();
		this.mapPanel.refreshMap();
		
		this.revalidate();
	}
	
	
	/**
	 * Refresh the data panel
	 */
	public void refreshDataPanel() {
		this.dataPanel.refreshData();
		this.revalidate();
	}
}
