package edu.smv.logview;

// Map is built off of http://worldwind.arc.nasa.gov/java/index.html
// Online API: http://builds.worldwind.arc.nasa.gov/worldwind-releases/1.5/docs/api/index.html
// Online tutorial

/* 
 * You will need:
 *  1) http://www.microsoft.com/en-us/download/details.aspx?id=21254
 *  2) Copy the follwing binaries to your Java/jre#/bin which can be found here https://jogamp.org/jogl/www/)
 *  	http://download.java.net/media/jogl/builds/archive/jsr-231-1.1.1/
 *  	a) gluegen-rt.dll
 *  	b) jogl.dll
 *  	c) jogl_awt.dll
 *  	d)jogl_cg.dll
 */


import javax.swing.*;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class MapPanel extends JPanel {

	public MapPanel(Main main) {
		WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(500, 400));
        this.add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2956528751085474636L;

	public void refreshMap() {
		// TODO Auto-generated method stub
		
	}

}
