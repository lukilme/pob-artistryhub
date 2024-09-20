package com.artistryhub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.artistryhub.service.Facade;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MainView {

	private JFrame frame;
	private static Facade facade = new Facade();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton ArtistButton = new JButton("Artist");
		ArtistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArtistView artistview = new ArtistView();
				artistview.getFrame().setVisible(true);
			}
		});
		ArtistButton.setBounds(142, 40, 130, 42);
		frame.getContentPane().add(ArtistButton);

		JButton CityButton = new JButton("City");
		CityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityView cityview = new CityView();
				cityview.getFrame().setVisible(true);
			}
		});
		CityButton.setBounds(142, 93, 130, 42);
		frame.getContentPane().add(CityButton);

		JButton PresentationButton = new JButton("Presentation");
		PresentationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresentationView presentationview = new PresentationView();
				presentationview.getFrame().setVisible(true);
			}
		});
		PresentationButton.setBounds(142, 146, 130, 42);
		frame.getContentPane().add(PresentationButton);
	}

}
