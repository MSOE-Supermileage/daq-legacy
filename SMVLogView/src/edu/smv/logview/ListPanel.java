package edu.smv.logview;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.smv.data.structure.DataNode;

public class ListPanel extends JScrollPane {
	private static final long serialVersionUID = 5693982072116941641L;
	private JPanel underlyingPanel;
	private DataNodeButton currentSelection;
	private Main main;

	
	
	public ListPanel(Main main) {
		this.main = main;
		this.underlyingPanel = new JPanel();
		this.setViewportView(underlyingPanel);
		this.refreshList();
	}
	
	
	public void refreshList(){
		underlyingPanel.removeAll();
		this.underlyingPanel.setLayout(new GridLayout(main.getDataNodes().size(), 1));
		
		for(DataNode node : main.getDataNodes()){
			DataNodeButton button = new DataNodeButton(node);
			
			button.addActionListener( new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DataNodeButton source = (DataNodeButton) e.getSource();
					main.setCurrentNode(source.getNode());
					
					if(currentSelection != null){
						currentSelection.setEnabled(true);
					}
					
					currentSelection = source;
					currentSelection.setEnabled(false);
					
					main.refreshDataPanel();
				}
				
			});
			
			
			underlyingPanel.add(button);
		}
		
		underlyingPanel.validate();
	}
	
	
	private class DataNodeButton extends JButton {
		private static final long serialVersionUID = 4104739325804709085L;
		private DataNode node;
		
		public DataNodeButton(DataNode node){
			super();
			this.setNode(node);
			this.setText(node.getName());
		}

		public DataNode getNode() {
			return node;
		}

		public void setNode(DataNode node) {
			this.node = node;
		}
	}
}
