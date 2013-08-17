package edu.smv.gui;

import javax.swing.JPanel;

import edu.smv.data.DataNode;

public class DriverDisplay extends JPanel {
	private static final long serialVersionUID = -4346683533200820317L;
	
	private GuagePanel guagePanel;
	
	
	/**
	 * Constructor
	 */
	public DriverDisplay(){
		this.addComponents();
	}
	
	
	/**
	 * Add components to the driver display
	 */
	private void addComponents(){
		this.add(guagePanel = new GuagePanel());
	}


	/**
	 * Refresh the data on the driver display
	 * @param node
	 */
	public void refresh(DataNode node) {
		guagePanel.setValueMPH(node.getMph());
		guagePanel.setValueRPM(node.getRpm());
	}
}
