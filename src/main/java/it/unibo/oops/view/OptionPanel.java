package it.unibo.oops.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

//import java.awt.event.ActionListener;
import it.unibo.oops.controller.gamestate.GameState;
//import it.unibo.oops.view.DrawView;

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
     */
    public OptionPanel(final int screenWidth, final int screenHeight, final DrawView drawView) {
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Settings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        super.add(titleLabel, BorderLayout.NORTH);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));


        JButton fullscreenButton = new JButton("Fullscreen");
        
        JButton screenSizeButton = new JButton("Screen Size");
        JPanel screenSizePanel = new JPanel(new BorderLayout(10, 0));
        JTextField screenSizeField = new JTextField(screenWidth + "x" + screenHeight, 10);
        screenSizeField.setHorizontalAlignment(JTextField.CENTER);
        screenSizeField.setFont(new Font("Arial", Font.PLAIN, 14));
 
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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            boolean isFullscreen = frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
            if (isFullscreen) {
                frame.setExtendedState(JFrame.NORMAL);
            } else {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }


    private void changeScreenSize2() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            // Get current window size
            int currentWidth = frame.getWidth();
            int currentHeight = frame.getHeight();
    
            // Show input dialog with current size pre-filled
            String input = JOptionPane.showInputDialog(frame, 
                    "Enter new size in format <width>x<height>:",
                    "Screen Size",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    currentWidth + "x" + currentHeight // Pre-fill with current size
            ).toString();
    
            // Validate input
            if (input != null && input.matches("\\d+x\\d+")) {
                String[] parts = input.split("x");
                try {
                    int width = Integer.parseInt(parts[0]);
                    int height = Integer.parseInt(parts[1]);
                    frame.setSize(width, height); // Update window size
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers only.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid format. Use <width>x<height> (e.g., 1090x180).", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void changeScreenSize(JTextField screenSizeField) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            String input = screenSizeField.getText().trim();

            if (input.matches("\\d+x\\d+")) {
                String[] parts = input.split("x");
                try {
                    int width = Integer.parseInt(parts[0]);
                    int height = Integer.parseInt(parts[1]);
                    frame.setSize(width, height);
                } catch (NumberFormatException e) {
                    screenSizeField.setText("Invalid! Use: 1090x180");
                }
            } else {
                screenSizeField.setText("Invalid! Use: 1090x180");
            }
        }
    }

    

}
