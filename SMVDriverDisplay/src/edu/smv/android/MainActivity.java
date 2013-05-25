package edu.smv.android;

import edu.smv.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	/**
	 * Method gets run when the activity is created.
	 * @param savedInstanceState
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.addActionListeners();
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
	        	//Do not change to self.
	            return true;
	        case R.id.menu_Display:
	        	intent = new Intent(MainActivity.this, DataDisplayActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Configure:
	        	intent = new Intent(MainActivity.this, ConfigActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Help:
	        	intent = new Intent(MainActivity.this, HelpActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return this.onOptionsItemSelected(item);
	    }
    }
    
    
    /**
     * Add action listeners to the GUI components on the screen.
     */
    private void addActionListeners() {
		final Button btnDisplay = (Button) findViewById(R.id.btnDisplay);
		final Button btnConfig = (Button) findViewById(R.id.btnConfigure);
		final Button btnHelp = (Button) findViewById(R.id.btnHelp);
		
		// Handle Display button action
		btnDisplay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DataDisplayActivity.class);
				startActivity(intent);
			}
		});

		// Handle Configure button action
		btnConfig.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
				startActivity(intent);
			}
		});
		
		// Handle Help button action
		btnHelp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, HelpActivity.class);
				startActivity(intent);
			}
		});
	}
    
}
