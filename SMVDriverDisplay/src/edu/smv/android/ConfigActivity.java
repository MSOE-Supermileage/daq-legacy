package edu.smv.android;

import java.io.File;

import edu.smv.data.Config;
import edu.smv.data.Logger;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ConfigActivity extends Activity {
	
	/**
	 * Method gets run when the activity is created.
	 * @param savedInstanceState
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_config);
        
        this.loadCurrentConfigs();
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
	        	intent = new Intent(ConfigActivity.this, MainActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Display:
	        	intent = new Intent(ConfigActivity.this, DataDisplayActivity.class);
				startActivity(intent);
	            return true;
	        case R.id.menu_Configure:
	        	//Do not change to self.
	            return true;
	        case R.id.menu_Help:
	        	intent = new Intent(ConfigActivity.this, HelpActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return this.onOptionsItemSelected(item);
	    }
    }
    
    
    /**
     * Add action listener to the GUI components
     */
    private void addActionListeners(){
    	final Button btnOk = (Button) findViewById(R.id.btnOk);
		final Button btnDefaults = (Button) findViewById(R.id.btnDefualts);
		final Button btnCancel = (Button) findViewById(R.id.btnCancel);
		
		// Handle Display button action
		btnOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveConfig();
			}
		});

		// Handle Configure button action
		btnDefaults.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				loadDefaultConfigs();
			}
		});
		
		// Handle Help button action
		btnCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				exitConfig();
			}
		});
    }
    
    
    /**
     * Load the current configuration into the EditViews
     */
    private void loadCurrentConfigs(){
    	this.setTextInEditViews(Config.getArdunioAddress(this), Config.getUUID_STRING(this), 
    			Config.getRefreshRate(this), Config.getLogRate(this), Config.getLogDirectory(this).getAbsolutePath());
    }
    
    
    /**
     * Load the default configuration into the EditViews
     */
    private void loadDefaultConfigs(){		
		this.setTextInEditViews(
				Config.getDefualtArdunioAddress(this), 
				Config.getDefualtUUID_String(this),
				Config.getDefaultRefreshRate(this),
				Config.getDefaultLogRate(this),
				Config.getDefualtLogDirectory(this).toString()
				);
    }
    
    
    /**
     * Set the Text in the EditViews
     * @param address
     * @param uuidHigh
     * @param uuidLow
     * @param d
     * @param e
     * @param logDir
     */
    private void setTextInEditViews(String address, String uuid, float d, float e, String logDir){
    	EditText edit_address = (EditText) this.findViewById(R.id.editAddress);
    	EditText edit_uuid = (EditText) this.findViewById(R.id.editUUID);
    	EditText edit_refreshRate = (EditText) this.findViewById(R.id.editRefershRate);
    	EditText edit_logRate = (EditText) this.findViewById(R.id.editLogRate);
    	EditText edit_logDirectory = (EditText) this.findViewById(R.id.editLogLocation);
    	
    	edit_address.setText(address);
    	edit_uuid.setText(uuid);
    	edit_refreshRate.setText("" + d);
    	edit_logRate.setText("" + e);
    	edit_logDirectory.setText(logDir);
    }
    
    
    /**
     * Exit the activity
     */
    private void exitConfig(){
    	this.finish();
    }
    
    
    /**
     * Save the activity
     */
    private void saveConfig(){  	
    	EditText edit_address = (EditText) this.findViewById(R.id.editAddress);
    	EditText edit_uuid = (EditText) this.findViewById(R.id.editUUID);
    	EditText edit_refreshRate = (EditText) this.findViewById(R.id.editRefershRate);
    	EditText edit_logRate = (EditText) this.findViewById(R.id.editLogRate);
    	EditText edit_logDirectory = (EditText) this.findViewById(R.id.editLogLocation);
    	
    	String address = null;
    	String uuid = null;
    	float refreshRate = -1;
    	float logRate = -1;
    	File logDir = null;
    	
    	address = edit_address.getText().toString();
    	
    	uuid = edit_uuid.getText().toString();
    	
    	try{
    		refreshRate = Float.parseFloat(edit_refreshRate.getText().toString());
    	}catch(NumberFormatException e){
    		AndroidUtil.showMessage(this, "The refresh rate must be a float.");
    		return;
    	}
    	
    	try{
    		logRate = Float.parseFloat(edit_logRate.getText().toString());
    	}catch(NumberFormatException e){
    		AndroidUtil.showMessage(this, "The log rate must be a float.");
    		return;
    	}
    	
    	logDir = new File(edit_logDirectory.getText().toString());
    	
    	Config.setArdunioAddress(this, address);
    	Config.setArdunioUUID(this, uuid);
    	Config.setRefreshRate(this, refreshRate);
    	Config.setLogRate(this, logRate);
    	Config.setLogDirectory(this, logDir);
    	exitConfig();
    }
}
