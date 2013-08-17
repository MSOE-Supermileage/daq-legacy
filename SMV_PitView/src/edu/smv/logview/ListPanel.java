package edu.smv.logview;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.smv.data.*;

public class ListPanel extends JPanel {
	private static final long serialVersionUID = 5693982072116941641L;
	private JScrollPane underlyingScrollPane;
	private JPanel underlyingPanel;
	
	private List<DataNodeButton> dataButtons;
	private JButton nextJButton;
	private JButton previousJButton;
	
	private Main main;
	
	/**
	 * Constructor
	 * @param main
	 */
	public ListPanel(Main main) {
		this.main = main;
		this.underlyingPanel = new JPanel();
	
		this.setLayout(new BorderLayout());
		
		this.underlyingScrollPane = new JScrollPane();
		this.underlyingScrollPane.setViewportView(underlyingPanel);
		this.add(this.underlyingScrollPane, BorderLayout.CENTER);
		this.add(this.getNodeNavigationPanel(), BorderLayout.SOUTH);
		
		this.refreshList();
	}


	/**
	 * Refresh the list
	 */
	public void refreshList(){
		this.underlyingPanel.removeAll();
		this.underlyingPanel.setLayout(new GridLayout(main.getDataNodes().size(), 1));
		this.dataButtons = new ArrayList<DataNodeButton>(main.getDataNodes().size());
		
		for(DataNode node : main.getDataNodes()){
			DataNodeButton button = new DataNodeButton(node);
			
			button.addActionListener( new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					DataNodeButton source = (DataNodeButton) e.getSource();
					setSelectedNode(source.getNode(), main.getCurrentNode());
					main.setCurrentNode(source.getNode());
				}
				
			});
			
			this.underlyingPanel.add(button);
			this.dataButtons.add(button);
		}
		
		this.underlyingScrollPane.setPreferredSize(this.getSize());
		
		this.underlyingPanel.validate();
		this.underlyingScrollPane.validate();
		this.validate();
	}
	
	
	/**
	 * A wrapper class to store the DataNode in the button it's connected too.
	 * @author Mark
	 *
	 */
	private class DataNodeButton extends JButton {
		private static final long serialVersionUID = 4104739325804709085L;
		private DataNode node;
		
		
		/**
		 * Constructor
		 * @param node
		 */
		public DataNodeButton(DataNode node){
			super();
			this.setNode(node);
			this.setText(node.getName());
		}

		
		/**
		 * Getter for the node
		 * @return
		 */
		public DataNode getNode() {
			return node;
		}

		
		/**
		 * Setter for the node
		 * @param node
		 */
		public void setNode(DataNode node) {
			this.node = node;
		}
		
		
		/**
		 * New equals method that compares the node wrapped in the class
		 */
		@Override
		public boolean equals(Object obj){
			try{
				DataNodeButton that = (DataNodeButton) obj;
				return this.node.equals(that.node);
			}catch (ClassCastException cce) {
				return false;
			}
		}
		
	}
	
	
	/**
	 * Get a navigation panel that lets you set the next and previous node
	 * @return
	 */
	private JPanel getNodeNavigationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		this.previousJButton  = new JButton("Previous");
		this.previousJButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(main.getDataNodes() != null && main.getDataNodes().size() > 0){
					int newIndex = 0;
				
					if(main.getCurrentNode() != null){
						int oldIndex = main.getDataNodes().indexOf(main.getCurrentNode());
						newIndex = oldIndex > 0 ? oldIndex - 1: oldIndex ;
					}
				
					setSelectedNode(main.getDataNodes().get(newIndex), main.getCurrentNode());
					main.setCurrentNode(main.getDataNodes().get(newIndex));
				}
			}
			
		});
		
		this.nextJButton = new JButton("Next");
		this.nextJButton.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(main.getDataNodes() != null && main.getDataNodes().size() > 0){
					int newIndex = 0;
				
					if(main.getCurrentNode() != null){
						int oldIndex = main.getDataNodes().indexOf(main.getCurrentNode());
						newIndex = oldIndex < main.getDataNodes().size() -1 ? oldIndex + 1: oldIndex ;
					}
				
					setSelectedNode(main.getDataNodes().get(newIndex), main.getCurrentNode());
					main.setCurrentNode(main.getDataNodes().get(newIndex));
				}
			}
		});
		
		
		panel.add(this.previousJButton);
		panel.add(this.nextJButton);
		
		panel.setSize(this.getWidth(), 20);
		
		return panel;
	}
	
	
	/**
	 * Disable the current node that is selected and enabled the previous node that was selected.
	 * @param newNode
	 * @param oldNode
	 */
	public void setSelectedNode(DataNode newNode, DataNode oldNode){
		int oldIndex;
		int newIndex;
		
		if(oldNode !=null){
			oldIndex = dataButtons.indexOf(new DataNodeButton(oldNode));
		}else{
			oldIndex = -1;
		}
		
		if(newNode !=null){
			newIndex = dataButtons.indexOf(new DataNodeButton(newNode));
		}else{
			newIndex = -1;
		}
		
		if(oldIndex != -1){
			dataButtons.get(oldIndex).setEnabled(true);
		}
		
		if(newIndex != -1){
			this.dataButtons.get(newIndex).setEnabled(false);
		}
		
		main.refreshDataPanel();
	}
}
