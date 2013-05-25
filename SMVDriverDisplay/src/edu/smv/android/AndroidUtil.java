package edu.smv.android;

import android.app.Activity;
import android.view.Gravity;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class AndroidUtil {
	
	/** 
     * Show a message to the user using a popup window.
     * @param text
     */
    public static void showMessage(Activity activity, String text){
    	PopupWindow popupWindow = new PopupWindow(activity);
    	
    	// Add a text view to the popup
    	TextView textView = new TextView(activity);
    	popupWindow.setContentView(textView);
    	LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	textView.setLayoutParams(layoutParams);
    	textView.setText(text);
    	
    	// Allows popup to disappear when screen is pressed
    	popupWindow.setOutsideTouchable(true);
    	
    	// Must set popup size for it to be visible
    	popupWindow.setHeight((int) (activity.getCurrentFocus().getHeight() * .75));
    	popupWindow.setWidth((int) (activity.getCurrentFocus().getWidth() * 0.75));
    	
    	// Display popup in the center of the screen
    	popupWindow.showAtLocation(activity.getCurrentFocus(), Gravity.CENTER, 0, 0);
    	popupWindow.update();
    }
}
