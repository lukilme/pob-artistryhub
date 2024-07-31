package com.artistryhub.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlternarLabelsSwing {

    private static boolean isLabel1Visible = true;

    public static void main(String[] args) {
        // Cria o frame principal
        JFrame frame = new JFrame("Alternar Labels");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        // Cria os labels
        JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);
        
        // Configura a visibilidade inicial
        label2.setVisible(false);

        // Cria o botão
        JButton button = new JButton("Alternar");

        // Adiciona ação ao botão
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alterna a visibilidade dos labels
                isLabel1Visible = !isLabel1Visible;
                label1.setVisible(isLabel1Visible);
                label2.setVisible(!isLabel1Visible);
            }
        });

        // Adiciona os componentes ao frame
        frame.add(label1, BorderLayout.CENTER);
        frame.add(label2, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        // Exibe o frame
        frame.setVisible(true);
    }
}

