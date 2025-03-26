package it.unibo.oops.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import it.unibo.oops.controller.GameState;

/**
*
*/
public class OptionPanel extends MyPanel {
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();
    //private final DrawView drawView;
            /**
             * @param screenWidth
             * @param screenHeight
             * @param drawView
             */
            public OptionPanel(final int screenWidth, final int screenHeight, final DrawView drawView) {
                super.setPreferredSize(new Dimension(screenWidth, screenHeight));
                super.setLayout(new BorderLayout());

                final JLabel titleLabel = new JLabel("Settings", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            super.add(titleLabel, BorderLayout.NORTH);

            final JPanel outerPanel = new JPanel(new BorderLayout());
            outerPanel.
            setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER));

        final JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS, GAP, GAP));


        JButton fullscreenButton = new JButton("Fullscreen");
        JButton screenSizeButton = new JButton("Screen Size");
        JButton volumeButton = new JButton("Volume");
        JButton sfxButton = new JButton("SFX");
        JButton returnButton = new JButton("Return");


        fullscreenButton.addActionListener(e -> toggleFullscreen());
        //screenSizeButton.addActionListener(e -> changeScreenSize());
        screenSizeField.addActionListener(e -> changeScreenSize(screenSizeField));
        screenSizeButton.addActionListener(e -> changeScreenSize(screenSizeField));
        screenSizePanel.add(screenSizeButton, BorderLayout.WEST);
        screenSizePanel.add(screenSizeField, BorderLayout.CENTER);

        returnButton.addActionListener(e -> drawView.changeGameState(GameState.TITLESTATE));

        buttonPanel.add(fullscreenButton);
        //buttonPanel.add(screenSizeButton);
        buttonPanel.add(screenSizePanel);
        buttonPanel.add(volumeButton);
        buttonPanel.add(sfxButton);
        buttonPanel.add(returnButton);

        
        outerPanel.add(buttonPanel, BorderLayout.CENTER);
        super.add(outerPanel, BorderLayout.WEST);
    }

    private void toggleFullscreen() {
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            final boolean isFullscreen = frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
            if (isFullscreen) {
                frame.setExtendedState(JFrame.NORMAL);
            } else {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }

    private void changeScreenSize() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            String widthInput = JOptionPane.showInputDialog(frame, "Enter width:", "Screen Size", JOptionPane.PLAIN_MESSAGE);
            String heightInput = JOptionPane.showInputDialog(frame, "Enter height:", "Screen Size", JOptionPane.PLAIN_MESSAGE);
            
            if (widthInput != null && heightInput != null) {
                try {
                    int width = Integer.parseInt(widthInput);
                    int height = Integer.parseInt(heightInput);
                    frame.setSize(width, height);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                screenSizeField.setText("Invalid! Use: 1090x180");
            }
        }
    }
}
