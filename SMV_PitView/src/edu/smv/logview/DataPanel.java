package edu.smv.logview;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.smv.data.*;

public class DataPanel extends JScrollPane {
	private static final long serialVersionUID = 7250747468294010946L;
	private JPanel underlyingPanel;
	private JTextArea dataLabel;
	private Main main;
	
	public DataPanel(Main main) {
		this.main = main;
		this.dataLabel = new JTextArea();
		
		this.underlyingPanel = new JPanel();
		this.underlyingPanel.setLayout(new GridLayout(1,1));
		this.underlyingPanel.add(this.dataLabel);
		
		this.setViewportView(underlyingPanel);
		this.setPreferredSize(new java.awt.Dimension(-1, 200));
		this.refreshData();
	}

	public void refreshData() {
		String text = null;
		DataNode currentNode = this.main.getCurrentNode();
		
		if(currentNode != null){
			text = currentNode.toString();
		} else {
			text = "No data node is currently selected.";
		}
		
		this.dataLabel.setText(text);
	}

}
