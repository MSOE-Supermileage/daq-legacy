package edu.smv.android;

import edu.smv.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private MainService driverDisplay;
	private final CharSequence TITLE = "MSOE Data Display";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.setTitle(this.TITLE);
        
        // Turn off sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        driverDisplay = new MainService(this);
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
    	driverDisplay.stopSelf();
    	driverDisplay = null; 
    }
    
    @Override
    public void setRequestedOrientation(int orientation){
    	// Ignore the parameter and
    	// Force orientation to always be landscape
    	// http://developer.android.com/reference/android/R.attr.html#screenOrientation
    	super.setRequestedOrientation(0);
    }
    
}
