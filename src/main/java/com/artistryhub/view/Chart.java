package com.artistryhub.view;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {
  public static void main(String args[]) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(60, "Sales", "January");
    dataset.addValue(150, "Sales", "February");
    dataset.addValue(180, "Sales", "January");
    dataset.addValue(260, "Sales", "February");
   
 
    
    
    JFreeChart chart = ChartFactory.createBarChart3D(
    	    "Monthly Sales",
    	    "Month",
    	    "Sales",
    	    dataset, PlotOrientation.VERTICAL, false, false, false);

    ChartPanel chartPanel = new ChartPanel(chart);
    JFrame frame = new JFrame();
    
    // Set the title of the frame
    frame.setTitle("Sales Chart");

    frame.setSize(800, 600);
    frame.setContentPane(chartPanel);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
