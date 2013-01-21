package edu.smv.android;

import edu.smv.android.R;
import edu.smv.data.DataBase;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainService extends Service {
	
	private Activity mainActivity;
	private boolean updateGUI;
	private boolean loggerOn;
	
	private final double DISPLAY_REFRESH = .25 * 1000; 	//in milliseconds
	private final double DATA_LOG		 = 1.0 * 1000;	//in milliseconds
	
	private DataBase data;
	
	/**
	 * Constructor
	 * @param mainActivity
	 */
	public MainService(Activity mainActivity) {
		this.mainActivity = mainActivity;
		this.updateGUI = true;
		this.loggerOn = false;
		this.data = new DataBase();
		
		this.addActionListeners();
		this.startDataHandler();
	}
	
	/**
	 * Required Unimplemented Method.
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	/**
	 * Getter for updateGUI
	 * @return
	 */
	 public boolean isUpdateGUI() {
			return updateGUI;
	}
	 
	 /**
	  * Setter for updateGUI
	  * @param updateGUI
	  */
	public void setUpdateGUI(boolean updateGUI) {
		this.updateGUI = updateGUI;
	}
	
	/**
	 * Start the data handling thread
	 */
	public void startDataHandler(){
		Thread dataHandler = new Thread(new DataHandler());
		dataHandler.start();
	}
	
	/**
	 * Add Action Listeners on the GUI
	 */
	private void addActionListeners(){
		final Button btnNewLoggerFile = (Button) mainActivity.findViewById(R.id.btnNewLoggerFile);
		btnNewLoggerFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.createNewLog();
            }
        });
		
		final ToggleButton btnToggleLogger = (ToggleButton) mainActivity.findViewById(R.id.BTNtoggleLogger);		
		btnToggleLogger.setText("OFF");
		btnToggleLogger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(data.getLogFile() == null){
            		data.createNewLog();
            	}
                loggerOn = !loggerOn;
                btnToggleLogger.setChecked(loggerOn);
            }
        });
	}
	
	/**
	 * Data Handler
	 * @author tutkowskim
	 *
	 */
	private class DataHandler implements Runnable {
		public void run(){
    		double lastTimeDisplayed = -1;
    		double lastTimeLogged = -1;
    		
    		while(true){
    			boolean displayData = (updateGUI) && (System.currentTimeMillis() - lastTimeDisplayed >= DISPLAY_REFRESH);
    			boolean logData = loggerOn && (System.currentTimeMillis() - lastTimeLogged >= DATA_LOG);
    			
    			// Get Data
    			if(displayData || logData){
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
	    		final ToggleButton btnToggleLogger = (ToggleButton) mainActivity.findViewById(R.id.BTNtoggleLogger);
	    		btnToggleLogger.setChecked(loggerOn);
	    		
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
