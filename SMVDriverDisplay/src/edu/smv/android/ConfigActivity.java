package edu.smv.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class ConfigActivity extends Activity {
	
	/**
	 * Method gets run when the activity is created.
	 * @param savedInstanceState
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_config);
    }

    
    /**
     * Method gets run when creating the options menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
