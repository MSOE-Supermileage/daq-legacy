package edu.smv.android;

import edu.smv.android.R;
import edu.smv.data.DataBase;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TextView;

public class MainService extends Service {

	private Activity mainActivity;
	private boolean updateGUI;

	private final double DISPLAY_REFRESH = .25 * 1000; // in milliseconds
	private final double DATA_LOG = 15 * 1000; // in milliseconds

	private DataBase data;

	public MainService(Activity mainActivity) {
		this.mainActivity = mainActivity;
		this.updateGUI = true;
		this.data = new DataBase();
		this.startDataHandler();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUpdateGUI() {
		return updateGUI;
	}

	public void setUpdateGUI(boolean updateGUI) {
		this.updateGUI = updateGUI;
	}

	public void startDataHandler() {
		Thread dataHandler = new Thread(new DataHandler());
		dataHandler.start();
	}

	private class DataHandler implements Runnable {
		public void run() {
			double lastTimeDisplayed = -1;
			double lastTimeLogged = -1;

			while (true) {
				boolean displayData = (updateGUI)
						&& (System.currentTimeMillis() - lastTimeDisplayed >= DISPLAY_REFRESH);
				boolean logData = System.currentTimeMillis() - lastTimeLogged >= DATA_LOG;

				// Get Data
				if ((displayData) || logData) {
					data.loadFromArduino();

					// Display Data
					if (displayData) {
						lastTimeDisplayed = System.currentTimeMillis();
						mainActivity.runOnUiThread(displayValues);
					}

					// Log Data
					if (logData) {
						lastTimeLogged = System.currentTimeMillis();
						data.logData();
					}
				}
			}
		}

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
					mphValue = (TextView) mainActivity
							.findViewById(R.id.lblMPH);
					rpmValue = (TextView) mainActivity
							.findViewById(R.id.lblRPM);
					mpgValue = (TextView) mainActivity
							.findViewById(R.id.lblMPG);
					amphValue = (TextView) mainActivity
							.findViewById(R.id.lblAmph);
					batteryVoltage = (TextView) mainActivity
							.findViewById(R.id.lblBatteryVoltage);

					Resources r = mphValue.getResources();
					mphStr = r.getString(R.string.mph);
					rpmStr = r.getString(R.string.rpm);
					mpgStr = r.getString(R.string.mpg);
					amphStr = r.getString(R.string.amph);
					batteryStr = r.getString(R.string.battery);

				}

				mphValue.setText(String.format(mphStr, data.getMph()));
				rpmValue.setText(String.format(rpmStr, data.getRpm()));
				mpgValue.setText(String.format(mpgStr, data.getMpg()));
				amphValue.setText(String.format(amphStr, data.getAmph()));
				batteryVoltage.setText(String.format(batteryStr,
						data.getBatteryVoltage()));
			}
		};
	}

}
