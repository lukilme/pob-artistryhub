
package com.artistryhub.view;

import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.WindowAdapter;


import javax.swing.JOptionPane;

import com.artistryhub.model.Artist;
import com.artistryhub.service.Facade;

public class ArtistView extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private Facade facade = new Facade();
    public ArtistView() {
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                facade.finish();
                System.exit(0); 
            }
        });
        initComponents();
        
    }

   
    @SuppressWarnings("unchecked")
    private void initComponents() {

        ArtistPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        InputGenre = new javax.swing.JTextField();
        InputName = new javax.swing.JTextField();
        GenreLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        OptionNameLabel = new javax.swing.JLabel();
        OptionGenreLabel = new javax.swing.JLabel();
        BiographyLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InputBiography = new javax.swing.JTextArea();
        OptionBiographyLabel = new javax.swing.JLabel();
        ButtonsLayout = new javax.swing.JPanel();
        createArtist = new javax.swing.JButton();
        deleteArtist = new javax.swing.JButton();
        updateArtist = new javax.swing.JButton();
        showArtist = new javax.swing.JButton();
        TitleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(0, 51, 153));
        setMaximumSize(new java.awt.Dimension(480, 400));
        setMinimumSize(new java.awt.Dimension(480, 400));

        ArtistPanel.setBackground(new java.awt.Color(0, 102, 255));
        ArtistPanel.setAlignmentX(0.0F);
        ArtistPanel.setAlignmentY(0.0F);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        InputGenre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InputGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputGenreActionPerformed(evt);
            }
        });

        InputName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InputName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNameActionPerformed(evt);
            }
        });

        GenreLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        GenreLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GenreLabel.setText("  Genre:");

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NameLabel.setText("  Name:");

        OptionNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        OptionNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        OptionNameLabel.setText(" name must be unique and without numbers");

        OptionGenreLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        OptionGenreLabel.setForeground(new java.awt.Color(51, 51, 51));
        OptionGenreLabel.setToolTipText("");

        BiographyLabel.setBackground(new java.awt.Color(255, 255, 255));
        BiographyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BiographyLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BiographyLabel.setText("  Biography:");

        InputBiography.setColumns(20);
        InputBiography.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InputBiography.setRows(5);
        jScrollPane1.setViewportView(InputBiography);

        OptionBiographyLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        OptionBiographyLabel.setForeground(new java.awt.Color(51, 51, 51));

        createArtist.setText("Create");
        createArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createArtistActionPerformed(evt);
            }
        });
        ButtonsLayout.add(createArtist);

        deleteArtist.setText("Delete");
        deleteArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteArtistActionPerformed(evt);
            }
        });
        ButtonsLayout.add(deleteArtist);

        updateArtist.setText("Update");
        updateArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateArtistActionPerformed(evt);
            }
        });
        ButtonsLayout.add(updateArtist);

        showArtist.setText("Show");
        showArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showArtistActionPerformed(evt);
            }
        });
        ButtonsLayout.add(showArtist);
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(GenreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(OptionNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(InputName, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(InputGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(OptionGenreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(BiographyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OptionBiographyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonsLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenreLabel)
                    .addComponent(NameLabel))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OptionGenreLabel)
                    .addComponent(OptionNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BiographyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OptionBiographyLabel)
                .addGap(55, 55, 55)
                .addComponent(ButtonsLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Artist Manger");

        javax.swing.GroupLayout ArtistPanelLayout = new javax.swing.GroupLayout(ArtistPanel);
        ArtistPanel.setLayout(ArtistPanelLayout);
        ArtistPanelLayout.setHorizontalGroup(
            ArtistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArtistPanelLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(ArtistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        ArtistPanelLayout.setVerticalGroup(
            ArtistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ArtistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ArtistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ArtistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        

        pack();
    }// </editor-fold>                        

    private void InputNameActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void InputGenreActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void createArtistActionPerformed(java.awt.event.ActionEvent evt) { 
    	String BioInput = this.InputBiography.getText();
    	String GenreInput = this.InputGenre.getText();
    	String NameInput = this.InputName.getText();
    	try {
    		this.facade.createArtist(NameInput, GenreInput, BioInput);
    		this.InputName.setText("");
    		this.InputGenre.setText("");
    		this.InputBiography.setText("");
            JOptionPane.showMessageDialog(null, "Create Artist!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);

    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
    	}
   
    }                                            

    private void updateArtistActionPerformed(java.awt.event.ActionEvent evt) { 
    	String BioInput = this.InputBiography.getText().replace("\n", "");;
    	String GenreInput = this.InputGenre.getText();
    	String NameInput = this.InputName.getText();
    	try {
  
    		facade.updateArtist(NameInput, GenreInput, BioInput);
    		this.InputName.setText("");
    		this.InputGenre.setText("");
    		this.InputBiography.setText("");
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
    	}
   
    }                                            

    private void deleteArtistActionPerformed(java.awt.event.ActionEvent evt) {
    	String NameInput = this.InputName.getText();
    	try {
    		this.facade.deleteArtist(NameInput);
    		this.InputName.setText("");
    		this.InputGenre.setText("");
    		this.InputBiography.setText("");
    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Confirmação", JOptionPane.INFORMATION_MESSAGE);

    	}
    }                                     

    private void showArtistActionPerformed(java.awt.event.ActionEvent evt) {      
    	String NameInput = this.InputName.getText();
    	try {
    		if(NameInput.length()>0) {
    			Artist artistFinded = facade.searchArtist(NameInput);
        		this.InputName.setText(artistFinded.getName());
        		this.InputGenre.setText(artistFinded.getGenre());
        		String[] finalBiography = artistFinded.getBiography().split("");
        		String Aux = "";
        		for(String letter : finalBiography) {
        			if(Aux.length()%64==0) {
        				Aux+="\n"+letter;
        			}else {
        				Aux+=letter;
        			}
        		}
        		this.InputBiography.setText(Aux);
   
    		}else {
    			List<Artist> artistList = facade.getAllArtist();
            	for(Artist artist : artistList) {
            		System.out.println(artist);
            	}
    		}
    		
    		
    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error in getAllArtists", "Confirmação", JOptionPane.INFORMATION_MESSAGE);

    	}
    	
    }                                          


    public static void main(String args[]) {
  
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArtistView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArtistView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArtistView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArtistView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArtistView().setVisible(true);
            }
        });
    }

    private javax.swing.JPanel ArtistPanel;
    private javax.swing.JLabel BiographyLabel;
    private javax.swing.JPanel ButtonsLayout;
    private javax.swing.JLabel GenreLabel;
    private javax.swing.JTextArea InputBiography;
    private javax.swing.JTextField InputGenre;
    private javax.swing.JTextField InputName;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel OptionBiographyLabel;
    private javax.swing.JLabel OptionGenreLabel;
    private javax.swing.JLabel OptionNameLabel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JButton createArtist;
    private javax.swing.JButton deleteArtist;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton showArtist;
    private javax.swing.JButton updateArtist;
    // End of variables declaration                   
}
