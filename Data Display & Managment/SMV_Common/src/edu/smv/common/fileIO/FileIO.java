package edu.smv.common.fileIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;
import java.util.Scanner;

import edu.smv.common.data.DataNode;

public class FileIO {

	/**
	 * Load a binary file of a list of nodes.
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<DataNode> loadDataNodes(File file) {
		List<DataNode> nodes = null;
		ObjectInputStream input = null;
		
		 try {
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			nodes = (List<DataNode>) input.readObject();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return nodes;
	}

	
	/**
	 * Save a binary file of a List of nodes.
	 * @param file
	 * @param dataNodes
	 */
	public static void saveDataNodes(File file, List<DataNode> dataNodes) {
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			output.writeObject(dataNodes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.flush();
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
     * createDirectory(File dir) - create a new directory at the location specfied by the passed file.
     * @param dir
     * @return
     */
    static public boolean createDirectory(File dir){
    	boolean retVal = false;
           
    	try{
    		if(!dir.exists())
    			retVal = dir.mkdir();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return retVal;
    }
    
    
    /**
     * Write a string to a text file
     * @param file
     * @param output
     * @return
     */
    static public boolean writeTextFile(File file, String output){
    	boolean retVal = false;
    	BufferedWriter writer = null;
    	
    	try {
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write(output);
    		retVal= true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	return retVal;
    }

    
    /**
     * Read a text file and return it has a string.
     * @param file
     * @param output
     * @return
     */
    static public String readTextFile(File file){
    	String retVal = null;
    	Scanner reader = null;
    	
    	try {
    		reader = new Scanner(file);
    		retVal = "";    		
    		
    		while(reader.hasNext()){
    			retVal += reader.nextLine();
    			
    			if(reader.hasNext()){
    				retVal += "\n";
    			}
    		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
    	
    	return retVal;
    }
}