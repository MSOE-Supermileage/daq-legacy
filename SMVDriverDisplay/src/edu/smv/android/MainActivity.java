package edu.smv.android;

import edu.smv.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private DataDisplay driverDisplay;
	private final CharSequence TITLE = "MSOE Data Display";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.setTitle(this.TITLE);
        
        // Turn off sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        // Set Orientation to landscape
        this.setRequestedOrientation(0);
        
        double displayRefresh = .25 * 1000;
        double logData = 1.0 * 1000;
        
        driverDisplay = new DataDisplay(this, displayRefresh, logData);
        Thread driverDisplayThread = new Thread(driverDisplay);
        driverDisplayThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	 // Turn off sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	driverDisplay.setUpdateGUI(true);
    }
    
    @Override
    protected void onPause(){
    	super.onResume();
    	driverDisplay.setUpdateGUI(false);
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	driverDisplay.terminateDataDisplay();
    }
    
    @Override
    public void setRequestedOrientation(int orientation){
    	// Do nothing on user orientation change
    }
    
}
