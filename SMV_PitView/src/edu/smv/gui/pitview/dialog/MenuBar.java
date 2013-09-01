package edu.smv.gui.pitview.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import edu.smv.common.data.DataNode;
import edu.smv.common.fileIO.FileIO;
import edu.smv.gui.pitview.frames.MainFrame;
import edu.smv.gui.pitview.networking.ClientHandler;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 168849161387643717L;
	private MainFrame mainApplication;
	
	/**
	 * Add a Menu
	 */
	public MenuBar(MainFrame main) {
		this.mainApplication = main;
		
		JMenu file = new JMenu("File");
		JMenu networking = new JMenu("Networking");
		JMenu help = new JMenu("Help");
		
		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newMethod();
			}
		});
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				open();
			}
		});
		
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAs();
			}
		});
		
		JMenuItem export = new JMenuItem("Export");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				export();
			}
		});
		
		JMenuItem connect = new JMenuItem("Connet");
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connect();
			}
		});
		
		JMenuItem disconnect = new JMenuItem("Disconnet");
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				disconnect();
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				help();
			}
		});
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				about();
			}
		});
		
		file.add(newItem);
		file.add(open);
		file.add(saveAs);
		file.add(export);
		file.add(exit);
		networking.add(connect);
		networking.add(disconnect);
		help.add(helpItem);
		help.add(about);
		
		this.add(file);
		this.add(networking);
		this.add(help);
	}

	
	/**
	 * Method called when New is pressed
	 */
	protected void newMethod() {
		this.mainApplication.getDataNodes().clear();
		this.mainApplication.refreshAll();
	}
	
	
	/**
	 * Method called when help is pressed
	 */
	protected void help() {
		// TODO: Create a more informative help message
		JOptionPane.showMessageDialog(this.mainApplication, "No one can help you now...", "Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/**
	 * Method called when about is pressed
	 */
	protected void about() {
		// TODO: Create a more informative about message
		JOptionPane.showMessageDialog(this.mainApplication, "Author: M@rk 7u7k0w5k1", "About", JOptionPane.INFORMATION_MESSAGE);
	}


	/**
	 * Method called when exit is pressed
	 */
	protected void exit() {
		System.exit(0);
	}


	/**
	 * Method called when Connect is pressed
	 */
	protected void connect() {
		// TODO Auto-generated method stub
		String serverInfo = JOptionPane.showInputDialog(this.mainApplication, "What server are your trying to connect to?\n Please enter in the form 192.168.7.2:1234.");
		String[] tokens = serverInfo.split(":");
		
		try {
			this.mainApplication.setClientHandle(new ClientHandler(tokens[0], Integer.parseInt(tokens[1]), this.mainApplication));
			this.mainApplication.getClientHandle().start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.mainApplication, "Unable to connect to sever.\n" + e.getMessage());
		}
	}
	
	
	/**
	 * Method called when Disconnect is pressed
	 */
	protected void disconnect() {
		try{
			this.mainApplication.getClientHandle().terminate();
		} catch(Exception e){
			JOptionPane.showMessageDialog(this.mainApplication, "Unable to disconnect from server.\n" + e.getMessage());
		}
	}
	
	
	/**
	 * Method called when export is pressed
	 */
	protected void export(){
		JFileChooser chooser = new JFileChooser();
		
		int chooserRetVal = chooser.showSaveDialog(this);
		if(chooserRetVal == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			
			String output = DataNode.getCSVheader();
			
			for(DataNode node : this.mainApplication.getDataNodes()){
				output += "\n" + node.getCSVline();
			}
			
			FileIO.writeTextFile(file, output);
		}
	}


	/**
	 * Method called when save as is pressed
	 */
	protected void saveAs() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new SMVFileFilter());
		
		int chooserRetVal = chooser.showSaveDialog(this);
		if(chooserRetVal == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			FileIO.saveDataNodes(file, this.mainApplication.getDataNodes());
		}
	}


	/**
	 * Method called when open is pressed
	 */
	protected void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new SMVFileFilter());
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int chooserRetVal = chooser.showOpenDialog(this);
		if(chooserRetVal == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			this.mainApplication.setDataNodes(FileIO.loadDataNodes(file));
			this.mainApplication.refreshAll();
		}
	}
	
	
	/**
	 * FileFilter
	 * @author Mark
	 *
	 */
	protected class SMVFileFilter extends FileFilter {
		//TODO: Get filter working correctly
		@Override
		public boolean accept(File file) {
			boolean retVal = file.isDirectory();
			
			if(!retVal){
				try{
					String[] tokens = file.getAbsolutePath().toString().split(".");
					String extension = tokens[tokens.length-1];
					retVal = (extension.equalsIgnoreCase(DataNode.FILE_TYPE));
				} catch(Exception e){ /* Do nothing */ 	}
			}
			return retVal;
		}

		@Override
		public String getDescription() {
			return "*." + DataNode.FILE_TYPE;
		}
	}
}


