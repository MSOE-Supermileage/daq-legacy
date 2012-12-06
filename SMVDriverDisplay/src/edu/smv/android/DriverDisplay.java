package edu.smv.android;

import edu.smv.android.R;
import edu.smv.data.DataBase;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.widget.TextView;

public class DriverDisplay extends Activity {
	private final CharSequence TITLE = "MSOE Data Display";
	private final double DISPLAY_REFRESH = .5 * 1000; 	//in milliseconds
	private final double DATA_LOG		 = 30 * 1000;	//in milliseconds
	
	private DataBase data;
	private Thread driverDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        this.setTitle(this.TITLE);
        this.setRequestedOrientation(0);
        
        data = new DataBase();
        driverDisplay = new Thread(dataHandler);
        driverDisplay.start();
    }
    
    @Override
    public void setRequestedOrientation(int orientation){
    	// Ignore the parameter and
    	// Force orientation to always be landscape
    	// http://developer.android.com/reference/android/R.attr.html#screenOrientation
    	super.setRequestedOrientation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	// No Change
    }
    
    @Override
    protected void onPause(){
    	super.onResume();
    	// Continue updating in background
    	// So don't pause the threads
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	driverDisplay = null; 
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      // Ignore orientation/keyboard change
      super.onConfigurationChanged(newConfig);
    }
    
    
    private Runnable dataHandler = new Runnable(){
    	public void run(){
    		double lastTimeDisplayed = -1;
    		double lastTimeLogged = -1;
    		
    		while(true){
    			boolean displayData = System.currentTimeMillis() - lastTimeDisplayed >= DISPLAY_REFRESH;
    			boolean logData = System.currentTimeMillis() - lastTimeLogged >= DATA_LOG;
    			
    			// Get Data
    			if(displayData || logData){
    				data.loadFromArduino();
    			
	    			// Display Data
	    			if(displayData){
	    				lastTimeDisplayed = System.currentTimeMillis();
	    				runOnUiThread(displayValues);
	    			}
	    			
	    			//logData
	    			if(logData){
	    				lastTimeLogged = System.currentTimeMillis();
	    				//data.logData();
	    			}
    			}
    		}
    	}
    };
    
    private Runnable displayValues = new Runnable(){
    	public void run(){
    		setContentView(R.layout.activity_main);
        	TextView mphValue = (TextView) findViewById(R.id.mphValue);
        	TextView rpmValue = (TextView) findViewById(R.id.rpmValue);
        	TextView mpgValue = (TextView) findViewById(R.id.mpgValue);
        	TextView batteryVoltage = (TextView) findViewById(R.id.bvVoltage);
    		
    		mphValue.setText("" + data.getMph());
    		rpmValue.setText("" + data.getRpm());
    		mpgValue.setText("" + data.getMpg());
    		batteryVoltage.setText("" + data.getBatteryVoltage());
    	}
	 };
}
