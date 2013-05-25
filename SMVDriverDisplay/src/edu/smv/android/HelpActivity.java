package edu.smv.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HelpActivity extends Activity {
	
	/**
	 * Method gets run when the activity is created.
	 * @param savedInstanceState
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_help);
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
	        	intent = new Intent(HelpActivity.this, MainActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Display:
	        	intent = new Intent(HelpActivity.this, DataDisplayActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Configure:
	        	intent = new Intent(HelpActivity.this, ConfigActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Help:
	        	//Do not change to self.
	            return true;
	        default:
	            return this.onOptionsItemSelected(item);
	    }
    }
}
