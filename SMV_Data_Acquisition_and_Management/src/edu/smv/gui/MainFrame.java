package edu.smv.gui;

import javax.swing.JFrame;

import edu.smv.data.DataNode;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1010815932172777393L;
	private static final String TITLE = "Data Acquisition and Managment";
	private DriverDisplay driverDisplay = new DriverDisplay();

	
	/**
	 * Constructor
	 */
	public MainFrame(){
		this.add(driverDisplay);
		this.setJFrameProperties();
		this.pack();
	}
	
	
	/**
	 * Set the properties to the JFrame
	 */
	private void setJFrameProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(TITLE);
		this.setVisible(true);
		this.setResizable(false);
	}


	/**
	 * Refresh the data on the GUI
	 * @param node
	 */
	public void refresh(DataNode node) {
		driverDisplay.refresh(node);
	}

}
