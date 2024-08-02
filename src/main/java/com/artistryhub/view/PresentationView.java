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

import com.artistryhub.model.Presentation;
import com.artistryhub.service.Facade;

import javax.swing.JScrollPane;

public class PresentationView {

	private JFrame frame;
	private JTextField cityInput;
	private JTextField artistInput;
	private JTextField ticketSoldInput;
	private JTextField ticketTotalInput;
	private JTextField durationInput;
	private JTextField ticketPriceInput;
	private JTextField dateInput;
	private JTextArea showPresentationData;
	private static Facade facade = new Facade();
	private JTextField idFiled;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresentationView window = new PresentationView();
					window.frame.setVisible(true);
					window.frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							facade.finish();
							System.exit(0);
						}
					});
				} catch (Exception e) {
					facade.finish();
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public PresentationView() {
		initialize();
	}

	public void setFacade(Facade newFacade) {
		facade = newFacade;
	}

	public void updateViewPresentation() {
		List<Presentation> allPresentations = facade.getAllPresentation();
		StringBuilder resultFinal = new StringBuilder();

		if (allPresentations != null) {
			for (Presentation presentation : allPresentations) {
				if (presentation != null && presentation.getCity() != null && presentation.getArtist() != null) {
					String subString = presentation.getId() + "-) City name: " + presentation.getCity().getName() + "\n"
							+ "Artist name: " + presentation.getArtist().getName() + "\n" + "Date: "
							+ presentation.getDate() + " Lucro: "
							+ (presentation.getPriceTicket() * presentation.getTicketsTotal()) + "R$\n" + "Duration: "
							+ presentation.getDuration() + "min\n"
							+ "-----------------------------------------------------------------------------------------\n";

					resultFinal.append(subString);
				}
			}
		}
		showPresentationData.setText(resultFinal.toString());

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 102, 204));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(10, 28, 433, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel cityLabel = new JLabel("Name of the city:");
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		cityLabel.setBounds(10, 11, 196, 23);
		panel.add(cityLabel);

		cityInput = new JTextField();
		cityInput.setBounds(10, 33, 196, 20);
		panel.add(cityInput);
		cityInput.setColumns(10);

		JLabel infoCity = new JLabel("artist needs to be in the database");
		infoCity.setForeground(new Color(0, 0, 0));
		infoCity.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoCity.setBounds(10, 56, 196, 14);
		panel.add(infoCity);

		JLabel artistLabel = new JLabel("Name of the Artist:");
		artistLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		artistLabel.setBounds(216, 17, 207, 14);
		panel.add(artistLabel);

		artistInput = new JTextField();
		artistInput.setBounds(216, 33, 207, 20);
		panel.add(artistInput);
		artistInput.setColumns(10);

		JLabel infoArtist = new JLabel("city needs to be in the database");
		infoArtist.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoArtist.setBounds(216, 56, 207, 14);
		panel.add(infoArtist);

		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String artist = artistInput.getText();
				String city = cityInput.getText();
				String date = dateInput.getText();
				String ticketSoldString = ticketSoldInput.getText().trim();
				String ticketTotalString = ticketTotalInput.getText().trim();
				String durationString = durationInput.getText().trim();
				String ticketPriceString = ticketPriceInput.getText().trim();

				int ticketSold = 0;
				int ticketTotal = 0;
				int duration = 0;
				double ticketPrice = 0.0;

				try {
					ticketSold = Integer.parseInt(ticketSoldString);
					ticketTotal = Integer.parseInt(ticketTotalString);
					duration = Integer.parseInt(durationString);
					ticketPrice = Double.parseDouble(ticketPriceString);
					facade.createPresentation(date, artist, city, ticketPrice, duration, ticketSold, ticketTotal);
					artistInput.setText("");
					cityInput.setText("");
					dateInput.setText("");
					ticketSoldInput.setText("");
					ticketTotalInput.setText("");
					durationInput.setText("");
					ticketPriceInput.setText("");
					updateViewPresentation();
				} catch (Exception error) {
					System.out.println(error);
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

				String artist = artistInput.getText();
				String city = cityInput.getText();
				String date = dateInput.getText();
				String id = idFiled.getText();

				try {
					if (id != null && !id.isEmpty()) {
				        facade.deletePresentation(Integer.parseInt(id));
				    } else {
				        facade.deletePresentation(date, artist, city);
				    }
					
					artistInput.setText("");
					cityInput.setText("");
					dateInput.setText("");
					ticketSoldInput.setText("");
					ticketTotalInput.setText("");
					durationInput.setText("");
					ticketPriceInput.setText("");
					updateViewPresentation();
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
				String artist = artistInput.getText();
				String city = cityInput.getText();
				String date = dateInput.getText();
				String id = idFiled.getText();

				try {
				    Presentation presentationFounded = null;
				    
				    if (id != null && !id.isEmpty()) {
				        presentationFounded = facade.searchPresentation(Integer.parseInt(id));
				    } else {
				        presentationFounded = facade.searchPresentation(artist, city, date);
				    }

				    if (presentationFounded == null) {
				        throw new Exception("Presentation not found");
				    } else {
				    	System.out.println(presentationFounded);
				        String artistName = presentationFounded.getArtist().getName();
				        String cityName = presentationFounded.getCity().getName();
				        String dateString = presentationFounded.getDate();

				        String ticketSold = String.valueOf(presentationFounded.getTicketsSold());
				        String ticketTotal = String.valueOf(presentationFounded.getTicketsTotal());
				        String duration = String.valueOf(presentationFounded.getDuration());
				        String ticketPrice = String.format("%.2f", presentationFounded.getPriceTicket()); // Formatar para 2 casas decimais

				        artistInput.setText(artistName);
				        cityInput.setText(cityName);
				        dateInput.setText(dateString);
				        ticketSoldInput.setText(ticketSold);
				        ticketTotalInput.setText(ticketTotal);
				        durationInput.setText(duration);
				        ticketPriceInput.setText(ticketPrice);
				        updateViewPresentation();
				    }
				} catch (NumberFormatException error) {
					System.out.println(error);
				    JOptionPane.showMessageDialog(null, "Invalid ID format: " + error.getMessage(), "Error",
				            JOptionPane.ERROR_MESSAGE);
				} catch (Exception error) {
					System.out.println(error);
				    JOptionPane.showMessageDialog(null, error.getMessage(), "Error",
				            JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		searchButton.setBounds(334, 366, 89, 23);
		panel.add(searchButton);

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String artist = artistInput.getText();
				String city = cityInput.getText();
				String date = dateInput.getText();
				String idString = idFiled.getText();
				String ticketSoldString = ticketSoldInput.getText().trim();
				String ticketTotalString = ticketTotalInput.getText().trim();
				String durationString = durationInput.getText().trim();
				String ticketPriceString = ticketPriceInput.getText().trim();

				int ticketSold = 0;
				int ticketTotal = 0;
				int id = 0;
				int duration = 0;
				double ticketPrice = 0.0;

				try {
					id = Integer.parseInt(idString);
					ticketSold = Integer.parseInt(ticketSoldString);
					ticketTotal = Integer.parseInt(ticketTotalString);
					duration = Integer.parseInt(durationString);
					ticketPrice = Double.parseDouble(ticketPriceString);
					facade.updatePresentation(id, date, artist, city, ticketPrice, duration, ticketSold, ticketTotal);
					artistInput.setText("");
					cityInput.setText("");
					dateInput.setText("");
					ticketSoldInput.setText("");
					ticketTotalInput.setText("");
					durationInput.setText("");
					ticketPriceInput.setText("");
					updateViewPresentation();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		updateButton.setBounds(235, 366, 89, 23);
		panel.add(updateButton);

		JLabel infoTicketSold = new JLabel("non-negative number");
		infoTicketSold.setForeground(Color.BLACK);
		infoTicketSold.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoTicketSold.setBounds(10, 126, 196, 14);
		panel.add(infoTicketSold);

		ticketSoldInput = new JTextField();
		ticketSoldInput.setColumns(10);
		ticketSoldInput.setBounds(10, 103, 196, 20);
		panel.add(ticketSoldInput);

		JLabel ticketSoldLabel = new JLabel("Ticket sold:");
		ticketSoldLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ticketSoldLabel.setBounds(10, 81, 196, 23);
		panel.add(ticketSoldLabel);

		JLabel infoTicketTotal = new JLabel("non-negative number");
		infoTicketTotal.setForeground(Color.BLACK);
		infoTicketTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoTicketTotal.setBounds(216, 126, 196, 14);
		panel.add(infoTicketTotal);

		ticketTotalInput = new JTextField();
		ticketTotalInput.setColumns(10);
		ticketTotalInput.setBounds(216, 103, 207, 20);
		panel.add(ticketTotalInput);

		JLabel ticketTotalLabel = new JLabel("Ticket total:");
		ticketTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ticketTotalLabel.setBounds(216, 81, 196, 23);
		panel.add(ticketTotalLabel);

		durationInput = new JTextField();
		durationInput.setColumns(10);
		durationInput.setBounds(10, 169, 196, 20);
		panel.add(durationInput);

		JLabel durationLabel = new JLabel("Duration:");
		durationLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		durationLabel.setBounds(10, 147, 196, 23);
		panel.add(durationLabel);

		JLabel infoDuration = new JLabel("duration in minutes");
		infoDuration.setForeground(Color.BLACK);
		infoDuration.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoDuration.setBounds(10, 192, 196, 14);
		panel.add(infoDuration);

		ticketPriceInput = new JTextField();
		ticketPriceInput.setColumns(10);
		ticketPriceInput.setBounds(216, 169, 196, 20);
		panel.add(ticketPriceInput);

		JLabel ticketPriceLabel = new JLabel("Ticket price:");
		ticketPriceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ticketPriceLabel.setBounds(216, 147, 196, 23);
		panel.add(ticketPriceLabel);

		JLabel infoTicketPrice = new JLabel("greater than zero");
		infoTicketPrice.setForeground(Color.BLACK);
		infoTicketPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoTicketPrice.setBounds(216, 192, 196, 14);
		panel.add(infoTicketPrice);

		dateInput = new JTextField();
		dateInput.setColumns(10);
		dateInput.setBounds(12, 245, 196, 20);
		panel.add(dateInput);

		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateLabel.setBounds(12, 223, 196, 23);
		panel.add(dateLabel);

		JLabel infoDate = new JLabel("format: dd/MM/yyyy");
		infoDate.setForeground(Color.BLACK);
		infoDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoDate.setBounds(12, 268, 196, 14);
		panel.add(infoDate);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(216, 223, 196, 23);
		panel.add(lblId);
		
		idFiled = new JTextField();
		idFiled.setColumns(10);
		idFiled.setBounds(216, 245, 196, 20);
		panel.add(idFiled);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(453, 28, 341, 400);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 321, 378);
		panel_1.add(scrollPane);

		showPresentationData = new JTextArea();
		updateViewPresentation();
		scrollPane.setViewportView(showPresentationData);

		showPresentationData.setEditable(false);
		frame.setResizable(false);
		frame.setBounds(100, 100, 820, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
