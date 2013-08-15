package edu.smv.logview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import edu.smv.data.structure.DataNode;
import edu.smv.fileIO.FileIO;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 168849161387643717L;
	private Main mainApplication;
	
	/**
	 * Add a Menu
	 */
	public MenuBar(Main main) {
		this.mainApplication = main;
		
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
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
		
		JMenuItem goLive = new JMenuItem("Go Live");
		goLive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goLive();
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
		
		file.add(open);
		file.add(saveAs);
		file.add(export);
		file.add(goLive);
		file.add(exit);
		help.add(helpItem);
		help.add(about);
		
		this.add(file);
		this.add(help);
	}

	protected void help() {
		// TODO: Create a more informative help message
		JOptionPane.showMessageDialog(this.mainApplication, "No one can help you now...", "Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void about() {
		// TODO: Create a more informative about message
		JOptionPane.showMessageDialog(this.mainApplication, "Author: M@rk 7u7k0w5k1", "About", JOptionPane.INFORMATION_MESSAGE);
	}


	protected void exit() {
		System.exit(0);
	}


	protected void goLive() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this.mainApplication, "This feature is not yet available.", "Go Live", JOptionPane.WARNING_MESSAGE);
	}
	
	
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


	protected void saveAs() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new SMVFileFilter());
		
		int chooserRetVal = chooser.showSaveDialog(this);
		if(chooserRetVal == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			FileIO.saveDataNodes(file, this.mainApplication.getDataNodes());
		}
	}


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


