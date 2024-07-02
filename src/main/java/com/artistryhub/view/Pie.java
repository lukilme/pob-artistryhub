package com.artistryhub.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Color;

import javax.swing.JFrame;

public class Pie {

	/*
	  * @param colorStr e.g. "#FFFFFF"
	  * @return
	 */
    public static void main(String[] args) {
        // Prepare data
        String[] categories = {"Dance", "Music", "Art","Dance1", "Music1", "Art1"};
        double[] values = {30, 50, 20,54,76,65};
        // Create a DefaultPieDataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < categories.length; i++) {
            dataset.setValue(categories[i], values[i]);
        }

        // Create a JFreeChart pie chart
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Product Sales", // Chart title
                dataset,          // Dataset
                true,             // Show legend
                true,             // Show tooltips
                false             // No URLs
        );
        chart.setBackgroundPaint(new Color(30, 30, 30));
        
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setSectionPaint("Dance", Color.RED);
        piePlot.setSectionPaint("Music", Color.YELLOW);
        piePlot.setSectionPaint("Art", Color.DARK_GRAY);
        piePlot.setSectionPaint("Dance1", new Color(150, 50, 50));
        piePlot.setSectionPaint("Music1",new Color(170, 70, 70));
        piePlot.setSectionPaint("Art1", new Color(190, 90, 90));
        

        // Customize chart appearance (optional)
        // ... set colors, fonts, labels, etc.

        // Display the chart
        
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Pie Chart Example");
        frame.setContentPane(chartPanel);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
