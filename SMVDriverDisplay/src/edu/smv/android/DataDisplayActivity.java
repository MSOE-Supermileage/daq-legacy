package edu.smv.android;

import edu.smv.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class DataDisplayActivity extends Activity {
	private DataDisplay driverDisplay;

	
	/**
	 * Method is called when the activity is created
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_datadisplay);
        
        // Turn off sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        //Start display thread
        driverDisplay = new DataDisplay(this);
        Thread driverDisplayThread = new Thread(driverDisplay);
        driverDisplayThread.start();
    }

    
    /**
     * Method is called when the menu is created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    /**
     * Method called when a menu item is selected
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	Intent intent;
    	 // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_Home:
	        	intent = new Intent(DataDisplayActivity.this, MainActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Display:
	        	//Do not change to self.
	            return true;
	        case R.id.menu_Configure:
	        	intent = new Intent(DataDisplayActivity.this, ConfigActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Help:
	        	intent = new Intent(DataDisplayActivity.this, HelpActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return this.onOptionsItemSelected(item);
	    }
    }
    
    
    /**
     * Method is called when the activity is resumed
     */
    @Override
    protected void onResume(){
    	super.onResume();
    	 // Turn off sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	driverDisplay.setUpdateGUI(true);
    }
    
    
    /**
     * Method is called when the activity is paused
     */
    @Override
    protected void onPause(){
    	super.onResume();
    	driverDisplay.setUpdateGUI(false);
    }
    
    
    /**
     * Method is called when the activity is destroyed
     */
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	driverDisplay.terminateDataDisplay();
    }
}
