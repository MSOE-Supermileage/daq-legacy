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
	private DataHandler dataHandler;
	
	private final double DISPLAY_REFRESH = .5 * 1000; 	//in milliseconds
	private final double DATA_LOG		 = 30 * 1000;	//in milliseconds
	
	
	public MainService(Activity mainActivity) {
		this.mainActivity = mainActivity;
		this.updateGUI = true;
		this.dataHandler = new DataHandler();
		
		this.dataHandler.start();
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
	
	private class DataHandler extends Thread {
		private DataBase data;
		
		public DataHandler(){
			data = new DataBase();
		}
		
		@Override
    	public void run(){
    		double lastTimeDisplayed = -1;
    		double lastTimeLogged = -1;
    		
    		while(true){
    			boolean displayData = System.currentTimeMillis() - lastTimeDisplayed >= DISPLAY_REFRESH;
    			boolean logData = System.currentTimeMillis() - lastTimeLogged >= DATA_LOG;
    			
    			// Get Data
    			if((displayData && updateGUI) || logData){
    				data.loadFromArduino();
    			
	    			// Display Data
	    			if(displayData && updateGUI){
	    				lastTimeDisplayed = System.currentTimeMillis();
	    				mainActivity.runOnUiThread(displayValues);
	    			}
	    			
	    			//logData
	    			if(logData){
	    				lastTimeLogged = System.currentTimeMillis();
	    				// data.logData();
	    			}
    			}
    		}
    	}
    
	    private Runnable displayValues = new Runnable() {
	    	
	    	public void run(){
	    		mainActivity.setContentView(R.layout.activity_main);
	        	TextView mphValue = (TextView) mainActivity.findViewById(R.id.mphValue);
	        	TextView rpmValue = (TextView) mainActivity.findViewById(R.id.rpmValue);
	        	TextView mpgValue = (TextView) mainActivity.findViewById(R.id.mpgValue);
	        	TextView batteryVoltage = (TextView) mainActivity.findViewById(R.id.bvVoltage);
	    		
	    		mphValue.setText("" + data.getMph());
	    		rpmValue.setText("" + data.getRpm());
	    		mpgValue.setText("" + data.getMpg());
	    		batteryVoltage.setText("" + data.getBatteryVoltage());
	    	}
		 };
	}

}
