package edu.smv.dam.gui;

import javax.swing.JFrame;

import edu.smv.common.data.DataNode;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1010815932172777393L;
	private static final String TITLE = "Data Acquisition and Managment";
	private DriverDisplay driverDisplay = new DriverDisplay();

	
	/**
	 * Constructor
	 */
	public MainFrame(){
		this.setJFrameProperties();
		this.add(driverDisplay);
	}
	
	
	/**
	 * Set the properties to the JFrame
	 */
	private void setJFrameProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(TITLE);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(656, 416);
	}


	/**
	 * Refresh the data on the GUI
	 * @param node
	 */
	public void refresh(DataNode node) {
		driverDisplay.refresh(node);
	}

}
