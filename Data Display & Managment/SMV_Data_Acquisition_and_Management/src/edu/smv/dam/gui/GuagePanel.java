package edu.smv.dam.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class GuagePanel extends JPanel {
	private static final long serialVersionUID = -3472679468327071122L;
	private final int DIAL_RADIUS = 325;
	private DefaultValueDataset rpmDataset;
	private DefaultValueDataset mphDataset;
	
	
	/**
	 * Constructor
	 */
	public GuagePanel(){
		this(0,0);
	}
	
	
	/**
	 * Constructor
	 * @param startingValueRPM
	 * @param startingValueMPH
	 */
	public GuagePanel(double startingValueRPM, double startingValueMPH){
        this.rpmDataset = new DefaultValueDataset(startingValueRPM);
        this.mphDataset = new DefaultValueDataset(startingValueMPH);
        
        this.addDialRPM();
        this.addDialMPH();
	}
	
	
	/**
	 * Set the value on the MPH Gauge
	 * @param value
	 */
	public void setValueMPH(double value){
		this.mphDataset.setValue(value);
	}
	
	
	/**
	 * Set the value of the RPM Gauge
	 * @param value
	 */
	public void setValueRPM(double value){
		this.rpmDataset.setValue(value/1000.0);
	}
	
	
	/**
	 * Add the MPH Gauge to the panel
	 */
	private void addDialMPH(){
        DialPlot plot = new DialPlot();
        plot.setView(0.0, 0.0, 1.0, 1.0);
        plot.setDataset(this.mphDataset);
        
        StandardDialFrame dialFrame = new StandardDialFrame();
        dialFrame.setBackgroundPaint(Color.lightGray);
        dialFrame.setForegroundPaint(Color.darkGray);
        plot.setDialFrame(dialFrame);
        
        GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground db = new DialBackground(gp);
        db.setGradientPaintTransformer(new StandardGradientPaintTransformer(
        GradientPaintTransformType.VERTICAL));
        plot.setBackground(db);

        DialValueIndicator dvi = new DialValueIndicator(0);
        plot.addLayer(dvi);
        
        StandardDialScale scale = new StandardDialScale(0, 30, -120, -300, 5, 1);
        scale.setTickRadius(0.88);
        scale.setTickLabelOffset(0.15);
        scale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
        plot.addScale(0, scale);

        DialPointer needle = new DialPointer.Pointer();
        plot.addLayer(needle);
        
        DialCap cap = new DialCap();
        cap.setRadius(0.10);
        plot.setCap(cap);
        
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("MPH");
        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(DIAL_RADIUS, DIAL_RADIUS));

        this.add(cp);
	}
	
	
	/**
	 * Add the RPM Gauge to the panel
	 */
	private void addDialRPM(){
        DialPlot plot = new DialPlot();
        plot.setView(0.0, 0.0, 1.0, 1.0);
        plot.setDataset(this.rpmDataset);
        
        StandardDialFrame dialFrame = new StandardDialFrame();
        dialFrame.setBackgroundPaint(Color.lightGray);
        dialFrame.setForegroundPaint(Color.darkGray);
        plot.setDialFrame(dialFrame);
        
        GradientPaint gp = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground db = new DialBackground(gp);
        db.setGradientPaintTransformer(new StandardGradientPaintTransformer(
        GradientPaintTransformType.VERTICAL));
        plot.setBackground(db);
        
        DialTextAnnotation annotation = new DialTextAnnotation("x 1000");
        annotation.setFont(new Font("Dialog", Font.BOLD, 14));
        annotation.setRadius(0.7);
        plot.addLayer(annotation);

        DialValueIndicator dvi = new DialValueIndicator(0);
        plot.addLayer(dvi);
        
        StandardDialScale scale = new StandardDialScale(0, 10, -120, -300, 1, 0);
        scale.setTickRadius(0.88);
        scale.setTickLabelOffset(0.15);
        scale.setTickLabelFont(new Font("Dialog", Font.PLAIN, 14));
        plot.addScale(0, scale);
        
        StandardDialRange range = new StandardDialRange(8, 10, Color.red);
        range.setInnerRadius(0.52);
        range.setOuterRadius(0.55);
        plot.addLayer(range);
        
        StandardDialRange range2 = new StandardDialRange(6, 8, Color.orange);
        range2.setInnerRadius(0.52);
        range2.setOuterRadius(0.55);
        plot.addLayer(range2);

        StandardDialRange range3 = new StandardDialRange(0, 6, Color.green);
        range3.setInnerRadius(0.52);
        range3.setOuterRadius(0.55);
        plot.addLayer(range3);

        DialPointer needle = new DialPointer.Pointer();
        plot.addLayer(needle);
        
        DialCap cap = new DialCap();
        cap.setRadius(0.10);
        plot.setCap(cap);
        
        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("RPM");
        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(DIAL_RADIUS, DIAL_RADIUS));

        this.add(cp);
	}
}
