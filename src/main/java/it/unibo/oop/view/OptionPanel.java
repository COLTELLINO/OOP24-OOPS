package it.unibo.oop.view;

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

import it.unibo.oop.controller.GameState;
/**
 * 
 */
public class OptionPanel extends MyPanel {
    private static final long serialVersionUID = 1L; 

    private static final int FONT_SIZE = 24;
    private static final int VERTICAL_BORDER = 10;
    private static final int ROWS = 3;
    private static final int COLUMNS = 2;
    private static final int GAP = 5;

    private final JTextField screenSizeField; // Declared here
    private final JPanel screenSizePanel;
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
        outerPanel.setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER, VERTICAL_BORDER));

        final JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS, GAP, GAP));

        final JButton fullscreenButton = new JButton("Fullscreen");
        final JButton screenSizeButton = new JButton("Screen Size");
        final JButton volumeButton = new JButton("Volume");
        final JButton sfxButton = new JButton("SFX");
        final JButton returnButton = new JButton("Return");

        fullscreenButton.addActionListener(e -> toggleFullscreen());
        returnButton.addActionListener(e -> drawView.changeGameState(GameState.TITLESTATE));

        // Fix: Initialize screenSizeField and screenSizePanel
        screenSizeField = new JTextField(10);
        screenSizePanel = new JPanel(new BorderLayout());

        screenSizeButton.addActionListener(e -> changeScreenSize());
        screenSizeField.addActionListener(e -> changeScreenSize());

        screenSizePanel.add(screenSizeButton, BorderLayout.WEST);
        screenSizePanel.add(screenSizeField, BorderLayout.CENTER);

        buttonPanel.add(fullscreenButton);
        buttonPanel.add(screenSizePanel); // Now correctly referenced
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
            frame.setExtendedState(isFullscreen ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
        }
    }

    private void changeScreenSize() {
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            final String widthInput = 
                JOptionPane.showInputDialog(frame, "Enter width:", "Screen Size", JOptionPane.PLAIN_MESSAGE);
            final String heightInput = 
                JOptionPane.showInputDialog(frame, "Enter height:", "Screen Size", JOptionPane.PLAIN_MESSAGE);

            if (widthInput != null && heightInput != null) {
                try {
                    final int width = Integer.parseInt(widthInput);
                    final int height = Integer.parseInt(heightInput);
                    frame.setSize(width, height);
                    screenSizeField.setText(width + "x" + height); // No more unresolved variable error
                } catch (NumberFormatException e) {
                    JOptionPane.
                    showMessageDialog(frame, "Invalid input. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                    screenSizeField.setText("Invalid! Use: 1090x180");
                }
            }
        }
    }
}
