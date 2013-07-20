package edu.smv.fileIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

import edu.smv.data.structure.DataNode;

public class FileIO {

	@SuppressWarnings({ "resource", "unchecked" })
	public static List<DataNode> loadDataNodes(File file) {
		List<DataNode> nodes = null;
		ObjectInput input = null;
		
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

	public static void saveDataNodes(File file, List<DataNode> dataNodes) {
		ObjectOutput output = null;
		
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

}
