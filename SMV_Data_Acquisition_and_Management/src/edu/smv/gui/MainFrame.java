package edu.smv.gui;

import javax.swing.JFrame;

import edu.smv.data.structure.DataNode;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4346683533200820317L;
	private static final String TITLE = "Data Acquisition and Managment";
	
	private GuagePanel guagePanel;
	
	
	/**
	 * Constructor
	 */
	public MainFrame(){
		this.setJFrameProperties();
		this.addComponents();
		this.pack();
	}
	
	
	private void setJFrameProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(TITLE);
		this.setVisible(true);
	}
	
	
	private void addComponents(){
		this.add(guagePanel = new GuagePanel());
	}


	public void refresh(DataNode node) {
		guagePanel.setValueMPH(node.getMph());
		guagePanel.setValueRPM(node.getRpm());
	}
}
