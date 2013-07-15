package edu.smv.android;

import edu.smv.android.R;
import edu.smv.data.Config;
import edu.smv.data.DataBase;
import edu.smv.data.Logger;
import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DataDisplay implements Runnable {

	private Activity dataDisplayActivity;
	private boolean terminateDataDisplay;
	private boolean updateGUI;
	private boolean loggerOn;

	private DataBase data;

	/**
	 * Constructor
	 * 
	 * @param dataDisplayActivity
	 */
	public DataDisplay(Activity dataDisplayActivity) {
		this.dataDisplayActivity = dataDisplayActivity;
		this.terminateDataDisplay = false;
		this.updateGUI = true;
		this.loggerOn = false;
		this.data = new DataBase(dataDisplayActivity);

		this.addActionListeners();
	}


	/**
	 * Getter for updateGUI
	 * 
	 * @return
	 */
	public boolean isUpdateGUI() {
		return updateGUI;
	}

	/**
	 * Setter for updateGUI
	 * 
	 * @param updateGUI
	 */
	public void setUpdateGUI(boolean updateGUI) {
		this.updateGUI = updateGUI;
	}
	
	
	/**
	 * Safely Terminate the Data Display Thread.
	 */
	public void terminateDataDisplay(){
		this.terminateDataDisplay = true;
	}

	
	/**
	 * Add Action Listeners on the GUI
	 */
	private void addActionListeners() {
		final Button btnNewLoggerFile = (Button) dataDisplayActivity
				.findViewById(R.id.btnNewLoggerFile);
		btnNewLoggerFile.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Logger.createNewFileCSV(dataDisplayActivity);
			}
		});

		final ToggleButton btnToggleLogger = (ToggleButton) dataDisplayActivity
				.findViewById(R.id.BTNtoggleLogger);
		btnToggleLogger.setText("OFF");
		btnToggleLogger.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (Logger.getCSVLogFile() == null) {
					Logger.createNewFileCSV(dataDisplayActivity);
				}
				loggerOn = !loggerOn;
				btnToggleLogger.setChecked(loggerOn);
			}
		});
	}

	
	/**
	 * Thread is running.
	 */
	public void run() {
		double lastTimeDisplayed = -1;
		double lastTimeLogged = -1;

		while (!terminateDataDisplay) {
			boolean displayData = (updateGUI) && (System.currentTimeMillis() - lastTimeDisplayed >= Config.getRefreshRate(dataDisplayActivity));
			boolean logData = loggerOn && ((System.currentTimeMillis() - lastTimeLogged) >= Config.getLogRate(dataDisplayActivity));

			// Get Data
			if (displayData || logData) {
				data.loadFromArduino();

				// Display Data
				if (displayData) {
					lastTimeDisplayed = System.currentTimeMillis();
					dataDisplayActivity.runOnUiThread(displayValues);
				}

				// Log Data
				if (logData) {
					lastTimeLogged = System.currentTimeMillis();
					data.logData(false);
				}
			}
		}
	}

	
	/**
	 * Private Runnable that updates GUI objects.
	 * 
	 * Must be run on the UI thread.
	 */
	private Runnable displayValues = new Runnable() {
		TextView mphValue = null;
		TextView rpmValue = null;
		TextView mpgValue = null;
		TextView amphValue = null;
		TextView batteryVoltage = null;

		String mphStr = null;
		String rpmStr = null;
		String mpgStr = null;
		String amphStr = null;
		String batteryStr = null;

		public void run() {
			if (mphValue == null) {
				mphValue = (TextView) dataDisplayActivity
						.findViewById(R.id.lblMPH);
				rpmValue = (TextView) dataDisplayActivity
						.findViewById(R.id.lblRPM);
				mpgValue = (TextView) dataDisplayActivity
						.findViewById(R.id.lblMPG);
				amphValue = (TextView) dataDisplayActivity
						.findViewById(R.id.lblAmph);
				batteryVoltage = (TextView) dataDisplayActivity
						.findViewById(R.id.lblBatteryVoltage);

				Resources r = mphValue.getResources();
				mphStr = r.getString(R.string.mph);
				rpmStr = r.getString(R.string.rpm);
				mpgStr = r.getString(R.string.mpg);
				amphStr = r.getString(R.string.amph);
				batteryStr = r.getString(R.string.battery);

			}
			
			mphValue.setText(String.format(mphStr, data.getLatestMPH()));
			rpmValue.setText(String.format(rpmStr, data.getLatestRPM()));
			mpgValue.setText(String.format(mpgStr, data.getLatestMPG()));
			amphValue.setText(String.format(amphStr, data.getLatestAMPH()));
			batteryVoltage.setText(String.format(batteryStr, data.getLatestBatteryVoltage()));
		}
	};
}
