package edu.smv.android;

import edu.smv.data.Config;
import edu.smv.data.Logger;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
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
        
        Config.loadCurrentConfig(this);
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
				exitConfig();
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
    	this.setTextInEditViews(Config.getArdunioAddress(), Config.getUUIDHigh(), 
    			Config.getUUIDLow(), Config.getRefreshRate(), 
    			Config.getLogRate(), Config.getLogDirectory());
    }
    
    
    /**
     * Load the default configuration into the EditViews
     */
    private void loadDefaultConfigs(){
    	Resources r = this.getResources();
		float refreshRate = Float.parseFloat(r.getString(R.string.configRefreshRate));
		float logRate = Float.parseFloat(r.getString(R.string.configLogRate));
		String logDirectory = Logger.getDefualtLogDirectory().getAbsolutePath();
		String address = r.getString(R.string.configArdunioAddress);
		byte uuidHigh = Byte.parseByte(r.getString(R.string.configUUIDHigh));
		byte uuidLow = Byte.parseByte(r.getString(R.string.configUUIDLow));
		
		this.setTextInEditViews(address, uuidHigh, uuidLow, refreshRate, logRate, logDirectory);
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
    private void setTextInEditViews(String address, byte uuidHigh, byte uuidLow, double d, double e, String logDir){
    	EditText edit_address = (EditText) this.findViewById(R.id.editAddress);
    	EditText edit_uuidHigh = (EditText) this.findViewById(R.id.editUUIDHigh);
    	EditText edit_uuidLow = (EditText) this.findViewById(R.id.editUUIDLow);
    	EditText edit_refreshRate = (EditText) this.findViewById(R.id.editRefershRate);
    	EditText edit_logRate = (EditText) this.findViewById(R.id.editLogRate);
    	EditText edit_logDirectory = (EditText) this.findViewById(R.id.editLogLocation);
    	
    	edit_address.setText(address);
    	edit_uuidHigh.setText("" + uuidHigh);
    	edit_uuidLow.setText("" + uuidLow);
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
    	EditText edit_uuidHigh = (EditText) this.findViewById(R.id.editUUIDHigh);
    	EditText edit_uuidLow = (EditText) this.findViewById(R.id.editUUIDLow);
    	EditText edit_refreshRate = (EditText) this.findViewById(R.id.editRefershRate);
    	EditText edit_logRate = (EditText) this.findViewById(R.id.editLogRate);
    	EditText edit_logDirectory = (EditText) this.findViewById(R.id.editLogLocation);
    	
    	String address = edit_address.getText().toString();
    	byte uuidHigh = Byte.parseByte(edit_uuidHigh.getText().toString());
    	byte uuidLow = Byte.parseByte(edit_uuidLow.getText().toString());
    	float refreshRate = Float.parseFloat(edit_refreshRate.getText().toString());
    	float logRate = Float.parseFloat(edit_logRate.getText().toString());
    	String logDir = edit_logDirectory.getText().toString();
    	
    	Config.setArdunioAddress(address);
    	Config.setArdunioUUIDHigh(uuidHigh);
    	Config.setArdunioUUIDLow(uuidLow);
    	Config.setRefreshRate(refreshRate);
    	Config.setLogRate(logRate);
    	Config.setLogDirectory(logDir);
    	Config.saveConfigFile(this);
    }
}
