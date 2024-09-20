package com.artistryhub.view;



import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.artistryhub.service.Facade;
import com.artistryhub.exception.ArtistException;
import com.artistryhub.model.Artist;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ArtistView {

	private JFrame frame;
	private JTextField nameInput;
	private JTextField genreInput;
	private JTextArea biographyInput;
	private JTextArea showArtistData;
	private static Facade facade = new Facade();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//	    EventQueue.invokeLater(new Runnable() {
//	        public void run() {
//	            try {
//	                ArtistView window = new ArtistView();
//	                window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	                window.frame.setVisible(true);
//	                window.frame.addWindowListener(new WindowAdapter() {
//	                    @Override
//	                    public void windowClosing(WindowEvent e) {
//	                    }
//	                });
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    });
//	}

	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public ArtistView() {
		initialize();
		frame.setVisible(true);
	}

	public void setFacade(Facade newFacade) {
		facade = newFacade;
	}

	public void updateViewArtist() {
		List<Artist> allArtist = facade.getAllArtist();
		String resultFinal = "";
		int counter = 1;
		for (Artist artist : allArtist) {
			String subString = counter + "-) name: " + artist.getName() + "<|> genre: " + artist.getGenre() + "\n\r"
					+ "biography: \n" + artist.getBiography() + "\nnumber of presentations: "
					+ artist.getPresentations().size() + "\n"
					+ "--------------------------------------------------------------------------------------------\n";
			resultFinal += subString;
			counter += 1;
		}
		showArtistData.setText(resultFinal);
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
		nameInput.setBounds(10, 33, 196, 20);
		panel.add(nameInput);
		nameInput.setColumns(10);

		JLabel infoName = new JLabel("Name must be unique");
		infoName.setForeground(new Color(0, 0, 0));
		infoName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoName.setBounds(10, 56, 196, 14);
		panel.add(infoName);

		JLabel genreLabel = new JLabel("Genre:");
		genreLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		genreLabel.setBounds(216, 17, 207, 14);
		panel.add(genreLabel);

		genreInput = new JTextField();
		genreInput.setBounds(216, 33, 207, 20);
		panel.add(genreInput);
		genreInput.setColumns(10);

		JLabel infoGenre = new JLabel("genre cannot have numbers");
		infoGenre.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoGenre.setBounds(216, 56, 207, 14);
		panel.add(infoGenre);

		JLabel biographyLabel = new JLabel("Biography:");
		biographyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		biographyLabel.setBounds(10, 81, 207, 23);
		panel.add(biographyLabel);

		biographyInput = new JTextArea();
		biographyInput.setBounds(10, 111, 413, 117);
		panel.add(biographyInput);

		JLabel infoBiography = new JLabel("minimum of 16 characters and maximum of 255");
		infoBiography.setFont(new Font("Tahoma", Font.PLAIN, 10));
		infoBiography.setBounds(10, 236, 413, 14);
		panel.add(infoBiography);

		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();
				String genre = genreInput.getText();
				String biography = biographyInput.getText();
				try {
					facade.createArtist(name, genre, biography);
					nameInput.setText("");
					genreInput.setText("");
					biographyInput.setText("");
					updateViewArtist();
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
					facade.deleteArtist(name);
					nameInput.setText("");
					updateViewArtist();
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
					Artist foundedArtist = facade.searchArtist(name);
					if (foundedArtist == null) {
						throw new ArtistException("Artist not found");
					}

					genreInput.setText(foundedArtist.getGenre());
					biographyInput.setText(foundedArtist.getBiography());
					updateViewArtist();
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
				String genre = genreInput.getText();
				String biography = biographyInput.getText();
				try {
					facade.updateArtist(name, genre, biography);
					nameInput.setText("");
					genreInput.setText("");
					biographyInput.setText("");
					updateViewArtist();
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 321, 378);
		panel_1.add(scrollPane);

		showArtistData = new JTextArea();
		scrollPane.setViewportView(showArtistData);
		showArtistData.setFont(new Font("Gadugi", Font.BOLD, 11));
		showArtistData.setEditable(false);
		updateViewArtist();
		frame.setResizable(false);
		frame.setBounds(100, 100, 820, 478);

	}
}
