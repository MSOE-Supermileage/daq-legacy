package edu.smv.logview;

// Map is built off of http://worldwind.arc.nasa.gov/java/index.html
// Online API: http://builds.worldwind.arc.nasa.gov/worldwind-releases/1.5/docs/api/index.html
// Online tutorial

/* 
 * You will need:
 *  1) http://www.microsoft.com/en-us/download/details.aspx?id=21254
 *  2) Copy the follwing binaries to your Java/jre#/bin
 *  	Download location: http://download.java.net/media/jogl/builds/archive/jsr-231-1.1.1/
 *  	a) gluegen-rt.dll
 *  	b) jogl.dll
 *  	c) jogl_awt.dll
 *  	d)jogl_cg.dll
 *  
 *  	Copy the correct DLLs for the system before invoking
 *  		See here: http://stackoverflow.com/questions/1611357/how-to-make-a-jar-file-that-include-dll-files
 */


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.smv.data.structure.*;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 2956528751085474636L;
	private WorldWindowGLCanvas worldCanvas;
	private RenderableLayer layer;
	private List<PointPlacemark> mapPoints;
	private Main main;
	
	public MapPanel(Main main) {
		this.main = main;
		
		this.worldCanvas = new WorldWindowGLCanvas();
		this.layer = new RenderableLayer();
		this.mapPoints = new ArrayList<PointPlacemark>();
		
		this.worldCanvas.setPreferredSize(new java.awt.Dimension(500, 400));
		this.worldCanvas.setModel(new BasicModel());
		
		this.setMinimumSize(worldCanvas.getSize());
        this.add(worldCanvas);
	}

	
	public void refreshMap() {
		this.removeAllRenderables();
		
		for(DataNode node : this.main.getDataNodes()){
			PointPlacemark mapPoint = this.addPlaceMarker(node.getLatitude(), node.getLongitude(), node.getAltitude());
			this.mapPoints.add(mapPoint);
		}
		
		// Fly to and zoom into the starting position
		this.flyTo(this.mapPoints.get(0).getPosition());
	}
	
	
	private PointPlacemark addPlaceMarker(double latitude, double longitude, double elevation){
		Position position = Position.fromDegrees(latitude, longitude, elevation);
		PointPlacemark mapPoint = new PointPlacemark(position);
		
		PointPlacemarkAttributes pointAttribute = new PointPlacemarkAttributes();
		pointAttribute.setImageColor(Color.red);
		pointAttribute.setLabelFont(Font.decode("Verdana-Bold-22"));
		pointAttribute.setLabelMaterial(Material.CYAN);
		mapPoint.setAttributes(pointAttribute);
		
		this.layer.addRenderable(mapPoint);
		return mapPoint;
	}
	
	
	private void removeAllRenderables(){
		this.layer.removeAllRenderables();
	}

	
	@SuppressWarnings("unused")
	private void removePlaceMarker(PointPlacemark mapPoint){
		this.layer.removeRenderable(mapPoint);
	}
	
	
	private void flyTo(Position pos){
		//TODO: 
	}
	
}
