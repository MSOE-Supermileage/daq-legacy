package edu.smv.android;

import edu.smv.android.R;
import edu.smv.data.DataBase;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.widget.TextView;

public class MainService extends Service {
	
	private Activity mainActivity;
	private boolean updateGUI;
	
	private final double DISPLAY_REFRESH = .25 * 1000; 	//in milliseconds
	private final double DATA_LOG		 = 15 * 1000;	//in milliseconds
	
	private DataBase data;
	
	public MainService(Activity mainActivity) {
		this.mainActivity = mainActivity;
		this.updateGUI = true;
		this.data = new DataBase();
		this.startDataHandler();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 public boolean isUpdateGUI() {
			return updateGUI;
	}

	public void setUpdateGUI(boolean updateGUI) {
		this.updateGUI = updateGUI;
	}
	
	public void startDataHandler(){
		Thread dataHandler = new Thread(new DataHandler());
		dataHandler.start();
	}
	
	private class DataHandler implements Runnable {
		public void run(){
    		double lastTimeDisplayed = -1;
    		double lastTimeLogged = -1;
    		
    		while(true){
    			boolean displayData = (updateGUI) && (System.currentTimeMillis() - lastTimeDisplayed >= DISPLAY_REFRESH);
    			boolean logData = System.currentTimeMillis() - lastTimeLogged >= DATA_LOG;
    			
    			// Get Data
    			if((displayData) || logData){
    				data.loadFromArduino();
    			
	    			// Display Data
	    			if(displayData){
	    				lastTimeDisplayed = System.currentTimeMillis();
	    				mainActivity.runOnUiThread(displayValues);
	    			}
	    			
	    			// Log Data
	    			if(logData){
	    				lastTimeLogged = System.currentTimeMillis();
	    				data.logData();
	    			}
    			}
    		}
    	}
    
	    private Runnable displayValues = new Runnable() {
	    	public void run(){
	        	TextView mphValue = (TextView) mainActivity.findViewById(R.id.mphValue);
	        	TextView rpmValue = (TextView) mainActivity.findViewById(R.id.rpmValue);
	        	TextView mpgValue = (TextView) mainActivity.findViewById(R.id.mpgValue);
	        	TextView ampgValue = (TextView) mainActivity.findViewById(R.id.amphValue);
	        	TextView batteryVoltage = (TextView) mainActivity.findViewById(R.id.bvVoltage);
	    		
	    		mphValue.setText("" + data.getMph());
	    		rpmValue.setText("" + data.getRpm());
	    		mpgValue.setText("" + data.getMpg());
	    		ampgValue.setText("" + data.getAmph());
	    		batteryVoltage.setText("" + data.getBatteryVoltage());
	    	}
		 };
	}

}
