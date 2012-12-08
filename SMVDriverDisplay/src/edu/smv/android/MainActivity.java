package edu.smv.android;

import edu.smv.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;

public class MainActivity extends Activity {
	@SuppressWarnings("unused")
	private MainService driverDisplay;
	private final CharSequence TITLE = "MSOE Data Display";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.setTitle(this.TITLE);
        this.setRequestedOrientation(0);
        
        driverDisplay = new MainService(this);
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
    	driverDisplay = null; 
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      // Ignore orientation/keyboard change
      super.onConfigurationChanged(newConfig);
    }
    
}
