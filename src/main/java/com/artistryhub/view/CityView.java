package com.artistryhub.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.artistryhub.exception.CityException;
import com.artistryhub.model.City;
import com.artistryhub.service.Facade;

public class CityView {

	private JFrame frame;
	private JTextField nameInput;
	private JTextArea showCityData;
	private static Facade facade = new Facade();
	private int index = 0;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CityView window = new CityView();
//					window.frame.setVisible(true);
//					window.frame.addWindowListener(new WindowAdapter() {
//						@Override
//						public void windowClosing(WindowEvent e) {
//							facade.finish();
//							System.exit(0);
//						}
//					});
//				} catch (Exception e) {
//					facade.finish();
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public CityView() {
		initialize();
	}

	public void setFacade(Facade newFacade) {
		facade = newFacade;
	}

	public void updateViewCity() {
		List<City> allCity = facade.getAllCities();
		String resultFinal = "";
		int counter = 1;
		for (City city : allCity) {
			String subString = counter + "-) name: " + city.getName() + "\n number of presentations: "
					+ +city.getPresentations().size() + "\n"
					+ "--------------------------------------------------------------------------------------------\n";
			resultFinal += subString;
			counter += 1;
		}
		showCityData.setText(resultFinal);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 102, 204));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 28, 433, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(10, 11, 196, 23);
		panel.add(nameLabel);

		nameInput = new JTextField();
		nameInput.setBounds(10, 32, 295, 20);
		panel.add(nameInput);
		nameInput.setColumns(10);

		JLabel infoName = new JLabel("Name must be unique");
		infoName.setForeground(new Color(0, 0, 0));
		infoName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoName.setBounds(10, 58, 196, 14);
		panel.add(infoName);

		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();

				try {
					facade.createCity(name);
					nameInput.setText("");
					updateViewCity();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		createButton.setBounds(10, 366, 89, 23);
		panel.add(createButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();

				try {
					facade.deleteCity(name);
					nameInput.setText("");
					updateViewCity();
				} catch (Exception error) {
					System.out.println(error);
					JOptionPane.showMessageDialog(null, error.getMessage(), "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		deleteButton.setBounds(117, 366, 89, 23);
		panel.add(deleteButton);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();

				try {
					City foundCity = facade.searchCity(name);
					if (foundCity == null) {
						throw new CityException("City not found");
					}
					JOptionPane.showMessageDialog(null, "City " + name + " exists", "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);
					index = facade.getIndexCity(foundCity); 
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		searchButton.setBounds(334, 366, 89, 23);
		panel.add(searchButton);

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();

				try {
					facade.updateCity(index, name);
					nameInput.setText("");
					updateViewCity();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		updateButton.setBounds(235, 366, 89, 23);
		panel.add(updateButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(453, 28, 341, 400);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		showCityData = new JTextArea();
		showCityData.setEditable(false);
		showCityData.setBounds(10, 11, 321, 378);
		panel_1.add(showCityData);
		updateViewCity();
		frame.setResizable(false);
		frame.setBounds(100, 100, 820, 478);
	

	}

}
